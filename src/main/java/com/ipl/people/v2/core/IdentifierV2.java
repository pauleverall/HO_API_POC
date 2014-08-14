package com.ipl.people.v2.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "homeofficev2.identifier")
@JsonIgnoreProperties
@JsonInclude(Include.NON_EMPTY)
public class IdentifierV2{

	@Id
	@GeneratedValue
	@Column(name = "Identifier_UID")
    private String identifierUID;
	
	@Column(name = "Person_UID" , nullable = false)
    private String personUID;
	
	@Column(name = "Identifier_Type" , nullable = false)
    private String identifierType;
	
	@Column(name = "Identifier" , nullable = false)
    private String identifier;

	public String getIdentifierUID() {
		return identifierUID;
	}

	public void setIdentifierUID(String identifierUID) {
		this.identifierUID = identifierUID;
	}

	public String getPersonUID() {
		return personUID;
	}

	public void setPersonUID(String personUID) {
		this.personUID = personUID;
	}

	public String getIdentifierType() {
		return identifierType;
	}

	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	

}
