package com.ipl.people.v2.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.ipl.people.v2.core.FindPersonV2;
import com.ipl.people.v2.dao.FindPersonDAO;
import com.sun.jersey.api.NotFoundException;

/**
 * Resource class exposing Find Person restful API's returning JSON response version2(latest).
 * 
 * @author Asha
 *
 */
@Path("/v2/people")
@Produces(MediaType.APPLICATION_JSON)
public class FindPersonResource {	

    private static final Logger LOGGER = LoggerFactory.getLogger(FindPersonResource.class);

	private final FindPersonDAO findPersonDAO;

	public FindPersonResource(FindPersonDAO findPersonDAO) {
		this.findPersonDAO = findPersonDAO;
	}
	
	/**
	 * Restful get API to find persons with the possible matches of all the inputs provided by the user.
	 * 
	 * @param name
	 * @param surname
	 * @param age
	 * @param dateOfBirth
	 * @param pncId
	 * @param niNumber
	 * @param driverNo
	 * @param croNo
	 * @param passportNo
	 * @param justification
	 * @param limit
	 * @param offset
	 * @return List of matched persons
	 * @throws Exception
	 */
	@GET
	@Timed
	@UnitOfWork
	public List<FindPersonV2> findPerson(@QueryParam("given_name") Optional<String> name, @QueryParam("surname") Optional<String> surname, @QueryParam("age")  Optional<String> age,  @QueryParam("date_of_birth")  Optional<String> dateOfBirth, @QueryParam("pnc_id") Optional<String> pncId, 
			@QueryParam("ni_number") Optional<String> niNumber, @QueryParam("driver_no") Optional<String> driverNo, @QueryParam("cro_no") Optional<String> croNo, @QueryParam("passport_no") Optional<String> passportNo, @QueryParam("justification") String justification, @QueryParam("limit") Optional<Integer> limit, @QueryParam("offset") Optional<Integer> offset) throws Exception {
		
		//Conditions to check input parameters
		if(justification==null || justification.length()==0){
			throw new NotFoundException("Justification is required.");
		}
		LOGGER.info("Justification for search '" + "/people" + "' -- "+ justification);
		
		if((pncId == null || !pncId.isPresent()) && (name == null || !name.isPresent()) && (surname == null || !surname.isPresent()) && (age==null || !age.isPresent()) && (dateOfBirth==null || !dateOfBirth.isPresent()) 
				&& (niNumber ==null || !niNumber.isPresent()) && (driverNo ==null || !driverNo.isPresent()) && (croNo ==null || !croNo.isPresent()) && (passportNo ==null || !passportNo.isPresent()) ){
			throw new NotFoundException("Atleast one search parameter required.");
		}
		if( (niNumber !=null && niNumber.isPresent() && niNumber.get().length() >0) && !( (driverNo ==null || !driverNo.isPresent()) && (croNo ==null || !croNo.isPresent()) &&  (passportNo ==null || !passportNo.isPresent()) )){
			throw new NotFoundException("Multiple Identifiers, provide either Ni number or Driver number or Cro number or Passport number.");
		}		
		if( (driverNo !=null && driverNo.isPresent() && driverNo.get().length() >0) && !( (niNumber ==null || !niNumber.isPresent()) && (croNo ==null || !croNo.isPresent()) &&  (passportNo ==null || !passportNo.isPresent()) )){
			throw new NotFoundException("Multiple Identifiers, provide either Ni number or Driver number or Cro number or Passport number.");
		}
		if( (croNo !=null && croNo.isPresent() && croNo.get().length() >0) && !( (niNumber ==null || !niNumber.isPresent()) && (driverNo ==null || !driverNo.isPresent()) &&  (passportNo ==null || !passportNo.isPresent()) )){
			throw new NotFoundException("Multiple Identifiers, provide either Ni number or Driver number or Cro number or Passport number.");
		}
		if( (passportNo !=null && passportNo.isPresent() && passportNo.get().length() >0) && !( (niNumber ==null || !niNumber.isPresent()) && (driverNo ==null || !driverNo.isPresent()) &&  (croNo ==null || !croNo.isPresent()) )){
			throw new NotFoundException("Multiple Identifiers, provide either Ni number or Driver number or Cro number or Passport number.");
		}
		if(limit !=null && limit.isPresent() && limit.get() >100) {
			throw new NotFoundException("Records exceed default maximum.");
		}
	
		//DAO call to find people
		List<FindPersonV2> people = findPersonDAO.findPerson(name, surname, age, dateOfBirth, pncId, niNumber, driverNo, croNo, passportNo, limit, offset );
		if (people == null || people.size() ==0) {
			throw new NotFoundException("No record found.");
		}
		return people;
		
	}

}
