package com.ipl.people.v1.dao;


import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;

import com.google.common.base.Optional;
import com.ipl.people.v1.core.Person;

/**
 * DAO class providing get person functionality for version1.
 * 
 * @author Asha
 *
 */
public class PersonDAO extends AbstractDAO<Person> {
    public PersonDAO(SessionFactory factory) {
        super(factory);
    }

    /**
     * API to get a person with provided person id.
     * 
     * @param id
     * @return person
     */
    public Optional<Person> findById(String id) {
        return Optional.fromNullable(get(id));
    }
}
