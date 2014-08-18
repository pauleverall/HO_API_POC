package com.ipl.people.v1.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model mapping Person table with all the required fields for Find Person API.
 * 
 * @author Asha
 *
 */
@Entity
@Table(name = "homeofficev1.person")
@JsonInclude(Include.NON_EMPTY)
public class FindPerson{

	@Id
	@GeneratedValue
	@Column(name = "Person_UID")
	@JsonProperty("person_uid")
    private String personUID;
	
	@Column(name = "Gender", nullable = false)
	@JsonProperty("gender")
    private String gender;
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Person_UID", insertable=false, updatable=false)
	@ElementCollection(targetClass=NameAlias.class)
	@JsonIgnore
	private Set<NameAlias> nameAlias;
	
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Person_UID", insertable=false, updatable=false)
	@ElementCollection(targetClass=DuplicatePerson.class)
	@JsonIgnore
	private Set<DuplicatePerson> duplicatePerson;
	
		
	public Set<DuplicatePerson> getDuplicatePerson() {
		return duplicatePerson;
	}

	public void setDuplicatePerson(Set<DuplicatePerson> duplicatePerson) {
		this.duplicatePerson = duplicatePerson;
	}
	
	@JsonProperty("related_records")
	public List<String> getRelatedRecords() {		
		if(this.duplicatePerson!=null && this.duplicatePerson.size()>0)
		{			
			List<String> relatedRecords = new ArrayList<String>();
			for(DuplicatePerson record : this.duplicatePerson) {
				StringBuilder relatedRecord = new StringBuilder("");
				if(StringUtils.isNotBlank(record.getRelationship())) {
					relatedRecord.append(record.getRelationship() + ":");
				}
				if(StringUtils.isNotBlank(record.getRelatedUID())) {
					relatedRecord.append(record.getRelatedUID());
				}
				relatedRecords.add(relatedRecord.toString());
			}
			return relatedRecords;
		}
		return null;
	}

	public Set<NameAlias> getNameAlias() {
		return nameAlias;
	}

	public void setNameAlias(Set<NameAlias> nameAlias) {
		this.nameAlias = nameAlias;
	}

	@JsonProperty("place_of_birth")
	public String getPlaceOfBirth() {
		if(this.nameAlias!=null && this.nameAlias.size()>0)
		{			
			for(NameAlias name : this.nameAlias) {
				if(StringUtils.isNotBlank(name.getNameType()) &&  name.getNameType().equals("P")) {
					return name.getPlaceOfBirth();
				}
			}
		}
		return null;
	}
	
	@JsonProperty("date_of_birth")
	public Date getDateOfBirth() {
		if(this.nameAlias!=null && this.nameAlias.size()>0)
		{			
			for(NameAlias name : this.nameAlias) {
				if(StringUtils.isNotBlank(name.getNameType()) &&  name.getNameType().equals("P")) {
					return name.getDateOfBirth();
				}
			}
		}
		return null;
	}
	
	@JsonProperty("surname")
	public String getSurname() {
		if(this.nameAlias!=null && this.nameAlias.size()>0)
		{			
			for(NameAlias name : this.nameAlias) {
				if(StringUtils.isNotBlank(name.getNameType()) &&  name.getNameType().equals("P")) {
					return name.getSurname();
				}
			}
		}
		return null;
	}
	
	@JsonProperty("given_name")
	public List<String> getGivenNames() {
		if(this.nameAlias!=null && this.nameAlias.size()>0)
		{			
			for(NameAlias name : this.nameAlias) {
				if(StringUtils.isNotBlank(name.getNameType()) &&  name.getNameType().equals("P")) {
					List<String> names = new ArrayList<String>();
					if(StringUtils.isNotBlank(name.getForename1())) {
						names.add(name.getForename1());
					}
					if(StringUtils.isNotBlank(name.getForename2())) {
						names.add(name.getForename2());
					}
					if(StringUtils.isNotBlank(name.getForename3())) {
						names.add(name.getForename3());
					}
					if(StringUtils.isNotBlank(name.getForename4())) {
						names.add(name.getForename4());
					}
					return names;
				}
			}
		}
		return null;
	}
	
	public String getPersonUID() {
		return personUID;
	}

	public void setPersonUID(String personUID) {
		this.personUID = personUID;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
