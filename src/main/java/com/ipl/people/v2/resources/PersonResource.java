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
 * Resource class exposing get person restful API's returning JSON response version2(latest).
 * 
 * @author Asha
 *
 */
@Path("/v2/people/{person_uid}")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {	

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);

	private final PersonDAO personDAO;

	public PersonResource(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	/**
	 * Restful get API to return a person with a person id.
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
	public String getPerson(@PathParam("person_uid") String personId, @QueryParam("justification") String justification, @QueryParam("fields") Optional<String> fields) throws Exception {
		
		//Conditions to check input parameters
		if(justification==null || justification.length()==0){
			throw new NotFoundException("Justification is required.");
		}
		
		LOGGER.info("Justification for search '" + "/people/" + personId + "' -- "+ justification);
		
		Set<String> fieldsList = null; 
		if(fields != null && fields.isPresent() &&  fields.get().length() > 0){
			String[] fieldsArray = fields.get().split(",");
			fieldsList = new HashSet<String>(Arrays.asList(fieldsArray));
		}
		
		PersonV2 person = findSafely(personId);
		
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
		try {
			response = mapper.writeValueAsString(person);
		} catch (JsonProcessingException e) {
			throw new Exception("Exception on Person transformation", e);
		}
		
		return response;
	}

	private PersonV2 findSafely(String personId) {
		
		//DAO call to get person
		final Optional<PersonV2> person = personDAO.findById(personId);
		if (person== null || !person.isPresent()) {
			throw new NotFoundException("No record found.");
		}
		return person.get();
	}
	

}
