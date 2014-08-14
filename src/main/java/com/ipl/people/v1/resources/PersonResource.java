package com.ipl.people.v1.resources;

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
import com.ipl.people.v1.core.Person;
import com.ipl.people.v1.dao.PersonDAO;
import com.sun.jersey.api.NotFoundException;

@Path("/v1/people/{person_uid}")
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
	public Person getPerson(@PathParam("person_uid") String personId, @QueryParam("justification") String justification) {
		if(justification==null || justification.length()==0){
			throw new NotFoundException("Justification is required.");
		}
		LOGGER.info("Justification for search '" + "/people/" + personId + "' -- "+ justification);
		return findSafely(personId);
	}

	private Person findSafely(String personId) {
		final Optional<Person> person = personDAO.findById(personId);
		if (!person.isPresent()) {
			throw new NotFoundException("No record found.");
		}
		return person.get();
	}
	

}
