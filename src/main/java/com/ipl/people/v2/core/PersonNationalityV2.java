package com.ipl.people.v2.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Data model mapping Persons nationality table.
 * 
 * @author Asha
 *
 */
@Entity
@Table(name = "homeofficev2.person_nationality")
@JsonIgnoreProperties
@JsonInclude(Include.NON_EMPTY)
public class PersonNationalityV2 {

	@Column(name = "Person_UID" , nullable = false)
    private String personUID;
	
	@Id
	@GeneratedValue
	@Column(name = "Nationality_UID")
    private String nationalityUID;

    public String getPersonUID() {
        return personUID;
    }

    public void setPersonUID(String personUID) {
        this.personUID = personUID;
    }

    public String getNationalityUID() {
        return nationalityUID;
    }

    public void setNationalityUID(String nationalityUID) {
        this.nationalityUID = nationalityUID;
    }

}
