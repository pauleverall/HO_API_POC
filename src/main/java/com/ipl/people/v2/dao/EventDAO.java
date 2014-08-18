package com.ipl.people.v2.dao;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.google.common.base.Optional;
import com.ipl.people.v2.core.EventV2;

/**
 * DAO class providing event retrieve and create functionalities for version2(latest).
 *  
 * @author Asha
 *
 */
public class EventDAO extends
		AbstractDAO<EventV2> {
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
	public List<EventV2> findByPersonId(String personId, Optional<Integer> limit, Optional<Integer> offset) {
		Query query = namedQuery("com.ipl.people.event.findByPersonIdV2");
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
	public List<EventV2> findByPersonId(String personId,  String type, Optional<Integer> limit, Optional<Integer> offset) {
		Query query = namedQuery("com.ipl.people.event.findByPersonIdAndTypeV2");
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
	public List<EventV2> findByPersonIdAndEventId(String personId, int eventId) {
		Query query = namedQuery("com.ipl.people.event.findByPersonIdAndEventIdV2");
		query.setString("personId", personId);
		query.setInteger("eventId", eventId);
		return list(query);
	}
	
	/**
	 * API to create an event.
	 * 
	 * @param event
	 * @return created event
	 */
	public EventV2 create(EventV2 event) {
        return  persist(event);
    }

}
