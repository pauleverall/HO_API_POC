package com.ipl.people.v2.dao;


import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;

import com.google.common.base.Optional;
import com.ipl.people.v2.core.PersonV2;

public class PersonDAO extends AbstractDAO<PersonV2> {
    public PersonDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<PersonV2> findById(String id) {
        return Optional.fromNullable(get(id));
    }
}
