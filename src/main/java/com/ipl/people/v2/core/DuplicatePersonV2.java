package com.ipl.people.v2.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "homeofficev2.duplicate_person")
@JsonIgnoreProperties
@JsonInclude(Include.NON_EMPTY)
public class DuplicatePersonV2 {

	
	@Column(name = "Person_UID" , nullable = false)
    private String personUID;
	
	@Id
	@Column(name = "Related_UID" , nullable = false)
    private String relatedUID;
	
	@Column(name = "Relationship" , nullable = true)
    private String relationship;

    public String getPersonUID() {
        return personUID;
    }

    public void setPersonUID(String personUID) {
        this.personUID = personUID;
    }

    public String getRelatedUID() {
        return relatedUID;
    }

    public void setRelatedUID(String relatedUID) {
        this.relatedUID = relatedUID;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
