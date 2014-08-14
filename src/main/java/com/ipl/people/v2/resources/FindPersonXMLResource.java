package com.ipl.people.v2.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
import com.ipl.people.v2.core.FindPersonV2;
import com.ipl.people.v2.dao.FindPersonDAO;
import com.sun.jersey.api.NotFoundException;

@Path("/v2/people.xml")
@Produces(MediaType.APPLICATION_XML)
public class FindPersonXMLResource {	

    private static final Logger LOGGER = LoggerFactory.getLogger(FindPersonXMLResource.class);

	private final FindPersonDAO findPersonDAO;	

	private final ProducerTemplate template;
	
	private final CamelContext camelContext;

	public FindPersonXMLResource(FindPersonDAO findPersonDAO, CamelContext camelContext, ProducerTemplate template) {
		this.findPersonDAO = findPersonDAO;
		this.camelContext =camelContext;
		this.template = template;
	}
	
	@GET
	@Timed
	@UnitOfWork
	public String findPerson(@QueryParam("given_name") Optional<String> name, @QueryParam("surname") Optional<String> surname, @QueryParam("age")  Optional<String> age,  @QueryParam("date_of_birth")  Optional<String> dateOfBirth, @QueryParam("pnc_id") Optional<String> pncId, 
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
		
		//XML transformation
		String response = null;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			String json = ow.writeValueAsString(people);
			camelContext.start();
			template.start();			
			response =  template.requestBody("direct:unmarshalPeople", json, String.class);	
			
		} catch (JsonProcessingException e) {
			throw new Exception("Exception on Events XML transformation", e);
		} finally {
			 camelContext.stop();
			 template.stop();
		}
		return response;
		
	}

}
