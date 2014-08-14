package com.ipl.people.v2.resources;

import io.dropwizard.hibernate.UnitOfWork;

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
import com.ipl.people.v2.core.PersonV2;
import com.ipl.people.v2.dao.PersonDAO;
import com.sun.jersey.api.NotFoundException;

@Path("/v2/people.xml/{person_uid}")
@Produces(MediaType.APPLICATION_XML)
public class PersonXMLResource {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersonXMLResource.class);

	private final PersonDAO personDAO;

	private final ProducerTemplate template;

	private final CamelContext camelContext;

	public PersonXMLResource(PersonDAO personDAO, CamelContext camelContext, ProducerTemplate template) {
		this.personDAO = personDAO;
		this.camelContext =camelContext;
		this.template = template;
	}

	@GET
	@Timed
	@UnitOfWork
	public String getPerson(@PathParam("person_uid") String personId,
			@QueryParam("justification") String justification) throws Exception {
		
		//Conditions to check input parameters
		if (justification == null || justification.length() == 0) {
			throw new NotFoundException("Justification is required.");
		}
		LOGGER.info("Justification for search '" + "/people/" + personId
				+ "' -- " + justification);
		
		//DAO call to get person
		final Optional<PersonV2> person = personDAO.findById(personId);
		if (!person.isPresent()) {
			throw new NotFoundException("No record found.");
		}
		
		//XML transformation
		String response = null;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			String json = ow.writeValueAsString(person.get());
			camelContext.start();
			template.start();			
			response =  template.requestBody("direct:unmarshalPerson", json, String.class);	
			
		} catch (JsonProcessingException e) {
			throw new Exception("Exception on Person XML transformation", e);
		} finally {
			 camelContext.stop();
			 template.stop();
		}
		return response;
	}

}
