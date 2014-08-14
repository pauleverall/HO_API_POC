package com.ipl.people.v2.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.ArrayList;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Optional;
import com.ipl.people.v2.core.EventV2;
import com.ipl.people.v2.dao.EventDAO;
import com.sun.jersey.api.NotFoundException;

@Path("/v2/people/{person_uid}/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EventResource.class);

	private final EventDAO eventDAO;

	public EventResource(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}

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

	@POST
	@Timed
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public EventV2 createPerson(EventV2 event, @PathParam("person_uid") String personId) {
		
		if(event.getReportDate()== null){
			throw new NotFoundException("Report date can not be empty.");
		}		
    	if(event.getForceCode()== null){
			throw new NotFoundException("Force code can not be empty.");
		}
    	
    	if(event.getEventType()== null){
			throw new NotFoundException("Event type can not be empty.");
		}
    	
    	Calendar cal = Calendar.getInstance();
        java.sql.Date currentDate = java.sql.Date.valueOf(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DATE) );
        
        //To compare dates only
        Calendar reportDateCal = Calendar.getInstance();
        reportDateCal.setTime(event.getReportDate());
        java.sql.Date reportDate = java.sql.Date.valueOf(reportDateCal.get(Calendar.YEAR) + "-" + (reportDateCal.get(Calendar.MONTH)+1) + "-" + reportDateCal.get(Calendar.DATE) );
        
    	if(reportDate.compareTo(currentDate) > 0) {
    		throw new NotFoundException("Report date can not be in future.");
    	}
    	
    	if(event.getEndDate() !=null) {
	    	//To compare dates only
	    	Calendar endDateCal = Calendar.getInstance();
	    	endDateCal.setTime(event.getEndDate());
	    	java.sql.Date endDate = java.sql.Date.valueOf(endDateCal.get(Calendar.YEAR) + "-" + (endDateCal.get(Calendar.MONTH)+1) + "-" + endDateCal.get(Calendar.DATE) );
	    	if(endDate.compareTo(currentDate) < 0) {
	    		throw new NotFoundException("End date can not be a past date.");
	    	}
    	}
    	
    	List<String> eventTypes = new ArrayList<String>();
    	eventTypes.add("AS");
    	eventTypes.add("RM");
    	eventTypes.add("CC");
    	eventTypes.add("DD");
    	eventTypes.add("OC");
    	eventTypes.add("CS");
    	eventTypes.add("CA");
    	eventTypes.add("DA");
    	String eventType = event.getEventType().toUpperCase();
    	if(!eventTypes.contains(eventType)) {
    		throw new NotFoundException("Event type does not match with expected values.");
    	}
    	event.setEventType(eventType);
    	
    	event.setEventDate(currentDate);
    	event.setPersonUID(personId);
		
		eventDAO.create(event);
		if(event.getEventUID() <= 0){
			throw new NotFoundException("Error Persisting the event.");
		}
		EventV2 response = new EventV2();
		response.setEventUID(event.getEventUID());
		return response;
	}

}
