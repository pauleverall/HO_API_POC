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
@Table(name = "homeofficev1.name_alias")
@JsonIgnoreProperties
@JsonInclude(Include.NON_EMPTY) 
public class NameAlias {

	@Id
	@GeneratedValue
	@Column(name = "Name_UID")
    private String nameUID;
	
	@Column(name = "Person_UID" , nullable = false)
    private String personUID;
	
	@Column(name = "Name_Type" , nullable = false)
    private String nameType;
	
	@Column(name = "Place_of_Birth" , nullable = true)
    private String placeOfBirth;
	
	@Column(name = "Date_of_Birth" , nullable = true)
    private Date dateOfBirth;
	
	@Column(name = "Forename1" , nullable = false)
    private String forename1;
	
	@Column(name = "Forename2" , nullable = true)
    private String forename2;
	
	@Column(name = "Forename3" , nullable = true)
    private String forename3;
	
	@Column(name = "Forename4" , nullable = true)
    private String forename4;
	
	@Column(name = "Surname" , nullable = false)
    private String surname;

	public String getNameUID() {
		return nameUID;
	}

	public void setNameUID(String nameUID) {
		this.nameUID = nameUID;
	}

	public String getPersonUID() {
		return personUID;
	}

	public void setPersonUID(String personUID) {
		this.personUID = personUID;
	}

	public String getNameType() {
		return nameType;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getForename1() {
		return forename1;
	}

	public void setForename1(String forename1) {
		this.forename1 = forename1;
	}

	public String getForename2() {
		return forename2;
	}

	public void setForename2(String forename2) {
		this.forename2 = forename2;
	}

	public String getForename3() {
		return forename3;
	}

	public void setForename3(String forename3) {
		this.forename3 = forename3;
	}

	public String getForename4() {
		return forename4;
	}

	public void setForename4(String forename4) {
		this.forename4 = forename4;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	
}
