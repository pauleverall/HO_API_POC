package com.ipl.people.v2.dao;

import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;

import com.google.common.base.Optional;
import com.ipl.people.v2.core.StatusesV2;

/**
 * DAO class providing retrieve functionality for standard set of acceptable
 * statuses.
 * 
 * @author Asha
 *
 */
public class StatusesDAO extends AbstractDAO<StatusesV2> {
	public StatusesDAO(SessionFactory factory) {
		super(factory);
	}

	/**
	 * API to returns a status object if provided status id is valid.
	 * 
	 * @param id
	 * @return Status
	 */
	public Optional<StatusesV2> findById(String id) {
		return Optional.fromNullable(get(id));
	}

}
