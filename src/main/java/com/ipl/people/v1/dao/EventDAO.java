package com.ipl.people.v1.dao;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.google.common.base.Optional;
import com.ipl.people.v1.core.Event;

/**
 * DAO class providing event retrieve functionalities for version1.
 *  
 * @author Asha
 *
 */
public class EventDAO extends
		AbstractDAO<Event> {
	public EventDAO(SessionFactory factory) {
		super(factory);
	}
	
	public static int recordsLimit = 20;    
    public static int recordsOffset = 0;

	/**
	 * API to fetch list of Events associated with a person.
	 * 
	 * @param personId
	 * @param limit
	 * @param offset
	 * @return list of events
	 */
	public List<Event> findByPersonId(String personId, Optional<Integer> limit, Optional<Integer> offset) {
		Query query = namedQuery("com.ipl.people.event.findByPersonId");
		query.setString("personId", personId);
		
		if(offset!=null && offset.isPresent() && offset.get() > 0)
    	{
			query.setFirstResult(offset.get());
    	} else {
    		query.setFirstResult(recordsOffset);
    	}
    	if(limit!=null && limit.isPresent()  && limit.get() > 0)
    	{
    		query.setMaxResults(limit.get());
    	} else {
    		query.setMaxResults(recordsLimit);
    	}
		return list(query);
	}
	
	
	/**
	 * API to fetch list of Events associated with a person with a specific type.
	 * 
	 * @param personId
	 * @param type
	 * @param limit
	 * @param offset
	 * @return list of events
	 */
	public List<Event> findByPersonId(String personId,  String type, Optional<Integer> limit, Optional<Integer> offset) {
		Query query = namedQuery("com.ipl.people.event.findByPersonIdAndType");
		query.setString("personId", personId);
		query.setString("type", "%" + type + "%");
		
		if(offset!=null && offset.isPresent() && offset.get() > 0)
    	{
			query.setFirstResult(offset.get());
    	} else {
    		query.setFirstResult(recordsOffset);
    	}
    	if(limit!=null && limit.isPresent()  && limit.get() > 0)
    	{
    		query.setMaxResults(limit.get());
    	} else {
    		query.setMaxResults(recordsLimit);
    	} 
		return list(query);
	}
	
	/**
	 * API to fetch an event associated with a person.
	 * 
	 * @param personId
	 * @param eventId
	 * @return event
	 */
	public List<Event> findByPersonIdAndEventId(String personId, int eventId) {
		Query query = namedQuery("com.ipl.people.event.findByPersonIdAndEventId");
		query.setString("personId", personId);
		query.setInteger("eventId", eventId);
		return list(query);
	}

}
