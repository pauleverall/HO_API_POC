package com.ipl.people.v1.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.ipl.people.v1.core.Event;
import com.ipl.people.v1.dao.EventDAO;
import com.sun.jersey.api.NotFoundException;

@Path("/v1/people/{person_uid}/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventResource.class);


	private final EventDAO wantedOrMissingPersonDAO;

	public EventResource(
			EventDAO wantedOrMissingPersonDAO) {
		this.wantedOrMissingPersonDAO = wantedOrMissingPersonDAO;
	}

	@GET
	@Timed
	@UnitOfWork
	public List<Event> getWantedOrMissingPerson(
			@PathParam("person_uid") String personId, 
			@QueryParam("type") Optional<String> type, 
			@QueryParam("limit") Optional<Integer> limit, 
			@QueryParam("offset") Optional<Integer> offset, 
			@QueryParam("justification") String justification) {
		if(justification==null || justification.length()==0){
			throw new NotFoundException("Justification is required.");
		}
		LOGGER.info("Justification for search '" + "/people/" + personId + "/events" + "' -- "+ justification);
		
		if(limit !=null && limit.isPresent() && limit.get() >100) {
			throw new NotFoundException("Records exceed default maximum.");
		}
		final List<Event> events;
		if(type !=null && type.isPresent()){
			events = wantedOrMissingPersonDAO.findByPersonId(personId, type.get(), limit, offset );
		} else {
			events = wantedOrMissingPersonDAO.findByPersonId(personId, limit, offset);
		}
		if (events == null || events.size() ==0) {
			throw new NotFoundException("No record found.");
		}
		return events;
	}

	@GET
	@Timed
	@UnitOfWork
	@Path("/{event_id}")
	public List<Event> getWantedOrMissingPersonWithEventId(
			@PathParam("person_uid") String personId,
			@PathParam("event_id") int eventId,  
			@QueryParam("justification") String justification) {
		
		if(justification==null || justification.length()==0){
			throw new NotFoundException("Justification is required.");
		}
		LOGGER.info("Justification for search '" + "/people/" + personId + "/events/" + eventId + "' -- "+ justification);
		final List<Event> events = wantedOrMissingPersonDAO.findByPersonIdAndEventId(personId, eventId);
		if (events == null || events.size() ==0) {
			throw new NotFoundException("No record found.");
		}
		return events;
	}

}
