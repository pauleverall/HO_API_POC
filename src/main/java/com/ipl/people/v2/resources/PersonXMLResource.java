package com.ipl.people.v2.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.common.base.Optional;
import com.ipl.people.v2.core.PersonV2;
import com.ipl.people.v2.dao.PersonDAO;
import com.sun.jersey.api.NotFoundException;

/**
 * Resource class exposing get person restful API's returning XML response.
 * 
 * @author Asha
 *
 */
@Path("/v2/people/{person_uid}.xml")
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

	/**
	 * Restful get API to return a person with a person id in XML format.
	 * 
	 * @param personId
	 * @param justification
	 * @param fields
	 * @return person
	 * @throws Exception
	 */
	@GET
	@Timed
	@UnitOfWork
	public String getPerson(@PathParam("person_uid") String personId,
			@QueryParam("justification") String justification, @QueryParam("fields") Optional<String> fields) throws Exception  {
		
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
		
		Set<String> fieldsList = null; 
		if(fields != null && fields.isPresent() &&  fields.get().length() > 0){
			String[] fieldsArray = fields.get().split(",");
			fieldsList = new HashSet<String>(Arrays.asList(fieldsArray));
		}
		
		//Fields filtering
		ObjectMapper mapper = new ObjectMapper(); 
		FilterProvider filters = null;
		if( fieldsList != null) {
			filters = new SimpleFilterProvider().addFilter("fieldsFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fieldsList));
		} else {
			filters = new SimpleFilterProvider().addFilter("fieldsFilter", SimpleBeanPropertyFilter.serializeAllExcept(""));
		}
		mapper.setFilters(filters);
		String response = null;

		//XML transformation
		try {
			String json = mapper.writeValueAsString(person.get());
			camelContext.start();
			template.start();			
			response =  template.requestBody("direct:unmarshalPerson", json, String.class);	
		} catch (JsonProcessingException e) {
			throw new Exception("Exception on Person transformation", e);
		} finally {
			 camelContext.stop();
			 template.stop();
		}		
			
		return response;
	}

}
