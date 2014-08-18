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
import com.ipl.people.v2.core.EventV2;
import com.ipl.people.v2.dao.EventDAO;
import com.sun.jersey.api.NotFoundException;

/**
 * Resource class exposing get an event restful API's returning XML response.
 * 
 * @author Asha
 *
 */
@Path("/v2/people/{person_uid}/events/{event_id}.xml")
@Produces(MediaType.APPLICATION_XML)
public class EventXMLResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventXMLResource.class);


	private final EventDAO eventDAO;
	
	private final ProducerTemplate template;
	
	private final CamelContext camelContext;

	public EventXMLResource(
			EventDAO eventDAO, CamelContext camelContext, ProducerTemplate template) {
		this.eventDAO = eventDAO;
		this.camelContext =camelContext;
		this.template = template;
	}

	/**
	 * Restful get API to return an event associated with a person in XML format.
	 * 
	 * @param personId
	 * @param eventId
	 * @param justification
	 * @return event in XML format
	 * @throws Exception
	 */
	@GET
	@Timed
	@UnitOfWork
	public String getWantedOrMissingPersonWithEventId(
			@PathParam("person_uid") String personId,
			@PathParam("event_id") int eventId,  
			@QueryParam("justification") String justification) throws Exception {
		
		//Conditions to check input parameters
		if(justification==null || justification.length()==0){
			throw new NotFoundException("Justification is required.");
		}
		LOGGER.info("Justification for search '" + "/people/" + personId + "/events/" + eventId + "' -- "+ justification);
		
		//DAO call to get event
		final List<EventV2> events = eventDAO.findByPersonIdAndEventId(personId, eventId);
		if (events == null || events.size() ==0) {
			throw new NotFoundException("No record found.");
		}
		
		//XML transformation
		String response = null;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			String json = ow.writeValueAsString(events.get(0));
						
			camelContext.start();
			template.start();			
			response =  template.requestBody("direct:unmarshalEvent", json, String.class);	
			
		} catch (JsonProcessingException e) {
			throw new Exception("Exception on Event XML transformation", e);
		} finally {
			 camelContext.stop();
			 template.stop();
		}
		return response;
	}

}
