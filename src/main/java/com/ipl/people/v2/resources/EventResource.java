package com.ipl.people.v2.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Optional;
import com.ipl.people.v2.core.EventV2;
import com.ipl.people.v2.core.PersonStatusV2;
import com.ipl.people.v2.dao.EventDAO;
import com.ipl.people.v2.dao.PersonStatusDAO;
import com.sun.jersey.api.NotFoundException;

/**
 * Resource class exposing event related restful API's returning JSON response version2(latest).
 * 
 * @author Asha
 *
 */
@Path("/v2/people/{person_uid}/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EventResource.class);

	private final EventDAO eventDAO;

	private final PersonStatusDAO personStatusDAO;

	private java.sql.Date currentDate;

	private final String AS = "AS";
	private final String RM = "RM";
	private final String CC = "CC";
	private final String DD = "DD";
	private final String CS = "CS";
	private final String OC = "OC";
	private final String CA = "CA";
	private final String DA = "DA";
	private final String CU = "CU";

	private final String WANTED = "1";
	private final String MISSING = "2";
	private final String CONVICTION = "8";
	private final String DISQUALIFIED_DRIVER = "6";

	public EventResource(EventDAO eventDAO, PersonStatusDAO personStatusDAO) {
		this.eventDAO = eventDAO;
		this.personStatusDAO = personStatusDAO;
	}

	/**
	 * Restful get API to return all the events associated with a person and type.
	 * 
	 * @param personId
	 * @param type
	 * @param limit
	 * @param offset
	 * @param justification
	 * @return list of events
	 */
	@GET
	@Timed
	@UnitOfWork
	public List<EventV2> getWantedOrMissingPerson(
			@PathParam("person_uid") String personId,
			@QueryParam("type") Optional<String> type,
			@QueryParam("limit") Optional<Integer> limit,
			@QueryParam("offset") Optional<Integer> offset,
			@QueryParam("justification") String justification) {

		// Conditions to check input parameters
		if (justification == null || justification.length() == 0) {
			throw new NotFoundException("Justification is required.");
		}
		LOGGER.info("Justification for search '" + "/people/" + personId
				+ "/events" + "' -- " + justification);

		if (limit != null && limit.isPresent() && limit.get() > 100) {
			throw new NotFoundException("Records exceed default maximum.");
		}

		// DAO call to get events
		final List<EventV2> events;
		if (type != null && type.isPresent()) {
			events = eventDAO.findByPersonId(personId, type.get(), limit,
					offset);
		} else {
			events = eventDAO.findByPersonId(personId, limit, offset);
		}
		if (events == null || events.size() == 0) {
			throw new NotFoundException("No record found.");
		}
		return events;
	}

	/**
	 * Restful get API to return an event associated with a person.
	 * 
	 * @param personId
	 * @param eventId
	 * @param justification
	 * @return Event
	 * @throws JsonProcessingException
	 */
	@GET
	@Timed
	@UnitOfWork
	@Path("/{event_id}")
	public EventV2 getWantedOrMissingPersonWithEventId(
			@PathParam("person_uid") String personId,
			@PathParam("event_id") int eventId,
			@QueryParam("justification") String justification)
			throws JsonProcessingException {

		// Conditions to check input parameters
		if (justification == null || justification.length() == 0) {
			throw new NotFoundException("Justification is required.");
		}
		LOGGER.info("Justification for search '" + "/people/" + personId
				+ "/events/" + eventId + "' -- " + justification);

		// DAO call to get event
		final List<EventV2> events = eventDAO.findByPersonIdAndEventId(
				personId, eventId);
		if (events == null || events.size() == 0) {
			throw new NotFoundException("No record found.");
		}

		return events.get(0);
	}

	/**
	 * Restful post API to create an event of a person.
	 * 
	 * @param event
	 * @param personId
	 * @return created event
	 */
	@POST
	@Timed
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public EventV2 createPerson(EventV2 event,
			@PathParam("person_uid") String personId) {

		// Input validation
		validateInput(event);

		event.setEventType(event.getEventType().toUpperCase());
		event.setEventDate(this.currentDate);
		event.setPersonUID(personId);

		// Create event
		eventDAO.create(event);
		if (event.getEventUID() <= 0) {
			throw new NotFoundException("Error Persisting the event.");
		}
		// Create status
		createStatus(event);

		EventV2 response = new EventV2();
		response.setEventUID(event.getEventUID());
		return response;
	}

	private void validateInput(EventV2 event) {
		// Input validation
		if (event.getReportDate() == null) {
			throw new NotFoundException("Report date can not be empty.");
		}
		if (StringUtils.isBlank(event.getForceCode())) {
			throw new NotFoundException("Force code can not be empty.");
		}

		if (StringUtils.isBlank(event.getEventType())) {
			throw new NotFoundException("Event type can not be empty.");
		}

		Calendar cal = Calendar.getInstance();
		this.currentDate = java.sql.Date.valueOf(cal.get(Calendar.YEAR) + "-"
				+ (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));

		// To compare dates only
		Calendar reportDateCal = Calendar.getInstance();
		reportDateCal.setTime(event.getReportDate());
		java.sql.Date reportDate = java.sql.Date.valueOf(reportDateCal
				.get(Calendar.YEAR)
				+ "-"
				+ (reportDateCal.get(Calendar.MONTH) + 1)
				+ "-"
				+ reportDateCal.get(Calendar.DATE));

		if (reportDate.compareTo(currentDate) > 0) {
			throw new NotFoundException("Report date can not be in future.");
		}

		if (event.getEndDate() != null) {
			// To compare dates only
			Calendar endDateCal = Calendar.getInstance();
			endDateCal.setTime(event.getEndDate());
			java.sql.Date endDate = java.sql.Date.valueOf(endDateCal
					.get(Calendar.YEAR)
					+ "-"
					+ (endDateCal.get(Calendar.MONTH) + 1)
					+ "-"
					+ endDateCal.get(Calendar.DATE));
			if (endDate.compareTo(currentDate) < 0) {
				throw new NotFoundException("End date can not be a past date.");
			}
		}

		String[] events = { AS, RM, CC, DD, OC, CS, CA, DA, CU };
		List<String> eventTypes = Arrays.asList(events);

		String eventType = event.getEventType().toUpperCase();
		if (!eventTypes.contains(eventType)) {
			throw new NotFoundException(
					"Event type does not match with expected values.");
		}
	}

	private void createStatus(EventV2 event) {
		PersonStatusV2 status = null;
		if (event.getEventType() != null
				&& (event.getEventType().equals(AS)
						|| event.getEventType().equals(RM)
						|| event.getEventType().equals(CC) || event
						.getEventType().equals(DD))) {

			status = new PersonStatusV2();
			status.setPersonUID(event.getPersonUID());
			status.setRelatedEventUID(event.getEventUID());
			status.setStatusExpiryDate(event.getEndDate());
			status.setStatusRecordDate(this.currentDate);
		}

		if (status != null) {
			if (event.getEventType().equals(AS)) {
				//create wanted status
				status.setStatusUID(WANTED);
				personStatusDAO.create(status);
			} else if (event.getEventType().equals(RM)) {
				//create missing status
				status.setStatusUID(MISSING);
				personStatusDAO.create(status);
			} else if (event.getEventType().equals(CC)) {
				//create conviction status
				status.setStatusUID(CONVICTION);
				personStatusDAO.create(status);
			} else if (event.getEventType().equals(DD)) {
				//create disqualified driver status
				status.setStatusUID(DISQUALIFIED_DRIVER);
				personStatusDAO.create(status);
			}
		} else if (event.getEventType() != null
				&& event.getEventType().equals(CU)) {
			//Delete wanted status		
			List<PersonStatusV2> statuses = personStatusDAO.findByPersonIdAndStatusId(event.getPersonUID(), WANTED);
			if (statuses != null && statuses.size() > 0) {
				for(PersonStatusV2 personStatus : statuses){
					personStatusDAO.delete(personStatus);
				}
			}
		}

	}

}
