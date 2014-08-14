package com.ipl.people.v1.dao;


import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;

import com.google.common.base.Optional;
import com.ipl.people.v1.core.Person;

public class PersonDAO extends AbstractDAO<Person> {
    public PersonDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Person> findById(String id) {
        return Optional.fromNullable(get(id));
    }
}
