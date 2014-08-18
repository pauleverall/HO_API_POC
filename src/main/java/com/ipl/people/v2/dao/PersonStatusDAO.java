package com.ipl.people.v2.dao;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ipl.people.v2.core.PersonStatusV2;


/**
 * DAO class providing retrieve, create and delete functionalities for person status.
 *  
 * @author Asha
 *
 */
public class PersonStatusDAO extends AbstractDAO<PersonStatusV2> {
	public PersonStatusDAO(SessionFactory factory) {
		super(factory);
	}

	/**
	 * Delete a status of a person.
	 * 
	 * @param status
	 */
	public void delete(PersonStatusV2 status) {
		Session session = currentSession();
		session.delete(status);
	}
	
	/**
	 * API to fetch all the statuses associated with a person.
	 * 
	 * @param personId
	 * @param statusId
	 * @return List of person status
	 */
	public List<PersonStatusV2> findByPersonIdAndStatusId(String personId, String statusId) {
		Query query = namedQuery("com.ipl.people.person.status.findByPersonIdAndStatusIdV2");
		query.setString("personId", personId);
		query.setString("statusId", statusId);
		return list(query);
	}
	
	/**
	 * API to set a status for a person.
	 * 
	 * @param status
	 * @return created status
	 */
	public PersonStatusV2 create(final PersonStatusV2 status) {
        switch (Integer.parseInt(status.getStatusUID()))
        {
            case 2: // Missing
            case 3: // High Threat Level
            case 4: // Medium Threat Level
            case 5: // Overstaying a Visa
            case 6: // Disqualified Driver
            case 9: // Breach of Visa Conditions
            	List<PersonStatusV2> existingRecords = findByPersonIdAndStatusId(status.getPersonUID(), status.getStatusUID());
                if (existingRecords == null || existingRecords.size()==0)
                {
                    return persist(status);
                }
                else
                {
                    return existingRecords.get(0);
                }
            default:
                return persist(status);
        }
    }

}
