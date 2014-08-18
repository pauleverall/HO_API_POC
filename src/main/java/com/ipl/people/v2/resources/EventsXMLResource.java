package com.ipl.people.v2.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Optional;
import com.ipl.people.v2.core.EventV2;
import com.ipl.people.v2.dao.EventDAO;
import com.sun.jersey.api.NotFoundException;

/**
 * Resource class exposing get all events restful API's returning XML response.
 * 
 * @author Asha
 *
 */
@Path("/v2/people/{person_uid}/events.xml")
@Produces(MediaType.APPLICATION_XML)
public class EventsXMLResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventsXMLResource.class);


	private final EventDAO eventDAO;
	
	private final ProducerTemplate template;
	
	private final CamelContext camelContext;

	public EventsXMLResource(
			EventDAO eventDAO, CamelContext camelContext, ProducerTemplate template) {
		this.eventDAO = eventDAO;
		this.camelContext =camelContext;
		this.template = template;
	}

	/**
	 * Restful get API to return all the events associated with a person and type in XML format.
	 * 
	 * @param personId
	 * @param type
	 * @param limit
	 * @param offset
	 * @param justification
	 * @return list of events in XML format
	 * @throws Exception
	 */
	@GET
	@Timed
	@UnitOfWork
	public String getWantedOrMissingPerson(
			@PathParam("person_uid") String personId, 
			@QueryParam("type") Optional<String> type, 
			@QueryParam("limit") Optional<Integer> limit, 
			@QueryParam("offset") Optional<Integer> offset, 
			@QueryParam("justification") String justification) throws Exception {
		
		//Conditions to check input parameters
		if(justification==null || justification.length()==0){
			throw new NotFoundException("Justification is required.");
		}
		LOGGER.info("Justification for search '" + "/people/" + personId + "/events" + "' -- "+ justification);
		
		if(limit !=null && limit.isPresent() && limit.get() >100) {
			throw new NotFoundException("Records exceed default maximum.");
		}
		
		//DAO call to get events
		final List<EventV2> events;
		if(type !=null && type.isPresent()){
			events = eventDAO.findByPersonId(personId, type.get(), limit, offset );
		} else {
			events = eventDAO.findByPersonId(personId, limit, offset);
		}
		if (events == null || events.size() ==0) {
			throw new NotFoundException("No record found.");
		}
		
		//XML transformation
		String response = null;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			String json = ow.writeValueAsString(events);			
			camelContext.start();
			template.start();			
			response =  template.requestBody("direct:unmarshalEvents", json, String.class);	
			
		} catch (JsonProcessingException e) {
			throw new Exception("Exception on Events XML transformation", e);
		} finally {
			 camelContext.stop();
			 template.stop();
		}
		return response;
	}

}
