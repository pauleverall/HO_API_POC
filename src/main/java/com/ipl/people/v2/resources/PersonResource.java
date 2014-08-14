package com.ipl.people.v2.resources;

import io.dropwizard.hibernate.UnitOfWork;

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
import com.ipl.people.v2.core.PersonV2;
import com.ipl.people.v2.dao.PersonDAO;
import com.sun.jersey.api.NotFoundException;

@Path("/v2/people/{person_uid}")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {	

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);

	private final PersonDAO personDAO;

	public PersonResource(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	@GET
	@Timed
	@UnitOfWork
	public PersonV2 getPerson(@PathParam("person_uid") String personId, @QueryParam("justification") String justification) {
		
		//Conditions to check input parameters
		if(justification==null || justification.length()==0){
			throw new NotFoundException("Justification is required.");
		}
		LOGGER.info("Justification for search '" + "/people/" + personId + "' -- "+ justification);
		/*
		 * ObjectMapper mapper = new ObjectMapper(); // first, construct filter
		 * provider to exclude all properties but 'name', bind it as 'myFilter'
		 * Set<String> val = new HashSet<String>(); val.add("event_id");
		 * val.add("offence"); FilterProvider filters = new
		 * SimpleFilterProvider().addFilter("myFilter",
		 * //SimpleBeanPropertyFilter.filterOutAllExcept(""));
		 * SimpleBeanPropertyFilter.serializeAllExcept("")); // and then
		 * serialize using that filter provider: mapper.setFilters(filters);
		 * String json =mapper.writeValueAsString(events.get(0));
		 * 
		 */

		/*ObjectMapper test = new ObjectMapper();
		EventV2 e = test.readValue(json, EventV2.class);
		System.out.println(e);*/
		return findSafely(personId);
	}

	private PersonV2 findSafely(String personId) {
		
		//DAO call to get person
		final Optional<PersonV2> person = personDAO.findById(personId);
		if (!person.isPresent()) {
			throw new NotFoundException("No record found.");
		}
		return person.get();
	}
	

}
