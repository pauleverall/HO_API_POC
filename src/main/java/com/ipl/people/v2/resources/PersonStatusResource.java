package com.ipl.people.v2.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.io.IOException;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.ipl.people.v2.core.PersonStatusV2;
import com.ipl.people.v2.core.StatusV2;
import com.ipl.people.v2.core.StatusesV2;
import com.ipl.people.v2.dao.PersonStatusDAO;
import com.ipl.people.v2.dao.StatusesDAO;
import com.sun.jersey.api.NotFoundException;

/**
 * Resource class exposing create person restful API's.
 * 
 * @author Asha
 *
 */
@Path("/v2/people/{person_uid}/status")
@Produces(MediaType.APPLICATION_JSON)
public class PersonStatusResource {

	private final PersonStatusDAO personStatusDAO;
	
	private final StatusesDAO statusesDAO;

	public PersonStatusResource(PersonStatusDAO personStatusDAO, StatusesDAO statusesDAO) {
		this.personStatusDAO = personStatusDAO;
		this.statusesDAO = statusesDAO;	
	}

	/**
	 * Restful post API to set a person status.
	 * 
	 * @param status
	 * @param personId
	 * @return created person status
	 * @throws IOException
	 */
	@POST
	@Timed
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public StatusV2 createPersonStatus(StatusV2 status, @PathParam("person_uid") String personId) throws IOException {
		
    	if(StringUtils.isBlank(status.getStatusUID())){
			throw new NotFoundException("Status id can not be empty.");
		}
    	
    	if(StringUtils.isBlank(status.getReason())){
			throw new NotFoundException("Reason can not be empty.");
		}
    	
    	Calendar cal = Calendar.getInstance();
        java.sql.Date currentDate = java.sql.Date.valueOf(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DATE) );
            	
    	if(status.getStatusExpiryDate() !=null) {
	    	//To compare dates only
	    	Calendar endDateCal = Calendar.getInstance();
	    	endDateCal.setTime(status.getStatusExpiryDate());
	    	java.sql.Date endDate = java.sql.Date.valueOf(endDateCal.get(Calendar.YEAR) + "-" + (endDateCal.get(Calendar.MONTH)+1) + "-" + endDateCal.get(Calendar.DATE));
	    	if(endDate.compareTo(currentDate) < 0) {
	    		throw new NotFoundException("Status Expiry date can not be a past date.");
	    	}
    	}
    	
    	//Check is the status valid
    	Optional<StatusesV2> statuses = statusesDAO.findById(status.getStatusUID());
    	if(statuses == null || !statuses.isPresent()) {
    		throw new NotFoundException("Status id does not match with expected values.");
    	}
    	
    	PersonStatusV2 personStatus = new PersonStatusV2();
    	personStatus.setPersonUID(personId);
    	personStatus.setStatusRecordDate(currentDate);
    	personStatus.setStatusUID(status.getStatusUID());
    	personStatus.setReason(status.getReason());
    	personStatus.setStatusExpiryDate(status.getStatusExpiryDate());
    	
    	personStatus = personStatusDAO.create(personStatus);
		if(personStatus.getId() <= 0){
			throw new NotFoundException("Error Persisting the status.");
		}
		StatusV2 response = new StatusV2();
		response.setId(personStatus.getId());
		return response;
	}

}
