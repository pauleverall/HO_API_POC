package com.ipl.people.v1.core;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "homeofficev1.alias_dob")
@JsonIgnoreProperties
@JsonInclude(Include.NON_EMPTY)
public class AliasDOB {

	@Id
	@GeneratedValue
	@Column(name = "Alias_DOB_UID")
    private String aliasDobUID;

	@Column(name = "Person_UID" , nullable = false)
    private String personUID;
	
	@Column(name = "Date_of_Birth" , nullable = false)
    private Date dateOfBirth;

	public String getAliasDobUID() {
		return aliasDobUID;
	}

	public void setAliasDobUID(String aliasDobUID) {
		this.aliasDobUID = aliasDobUID;
	}

	public String getPersonUID() {
		return personUID;
	}

	public void setPersonUID(String personUID) {
		this.personUID = personUID;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
