package com.ipl.people.authentication;


import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * DAO class providing Authentication functionalities.
 *  
 * @author Asha
 *
 */
public class AuthenticationDAO extends AbstractDAO<Authentication> {
	private SessionFactory sessionFactory;
	
    public AuthenticationDAO(SessionFactory factory) {    	
        super(factory);
        this.sessionFactory = factory;
    }
    
    /**
     * Retrieves Authentication object if the access token provided is valid else return null.
     *  
     * @param Access token
     * @return Authentication object
     * @throws Exception
     */
    public Authentication findById(String id) throws Exception {
    	final Session session = sessionFactory.openSession();
    	Authentication guid = null;
        try {
            try {
            	guid = (Authentication)session.get(Authentication.class, id);
            } catch (Exception e) {
            	throw e;
            }
        } finally {
            session.close();
        }
        return guid;
    }
}
