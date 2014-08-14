package com.ipl.people.v2.core;

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

@Entity
@Table(name = "homeofficev2.person")
@JsonInclude(Include.NON_EMPTY)
public class FindPersonV2{

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
	@ElementCollection(targetClass=NameAliasV2.class)
	@JsonIgnore
	private Set<NameAliasV2> nameAlias;
	
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Person_UID", insertable=false, updatable=false)
	@ElementCollection(targetClass=DuplicatePersonV2.class)
	@JsonIgnore
	private Set<DuplicatePersonV2> duplicatePerson;
	
		
	public Set<DuplicatePersonV2> getDuplicatePerson() {
		return duplicatePerson;
	}

	public void setDuplicatePerson(Set<DuplicatePersonV2> duplicatePerson) {
		this.duplicatePerson = duplicatePerson;
	}
	
	@JsonProperty("related_records")
	public List<String> getRelatedRecords() {		
		if(this.duplicatePerson!=null && this.duplicatePerson.size()>0)
		{			
			List<String> relatedRecords = new ArrayList<String>();
			for(DuplicatePersonV2 record : this.duplicatePerson) {
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

	public Set<NameAliasV2> getNameAlias() {
		return nameAlias;
	}

	public void setNameAlias(Set<NameAliasV2> nameAlias) {
		this.nameAlias = nameAlias;
	}

	@JsonProperty("place_of_birth")
	public String getPlaceOfBirth() {
		if(this.nameAlias!=null && this.nameAlias.size()>0)
		{			
			for(NameAliasV2 name : this.nameAlias) {
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
			for(NameAliasV2 name : this.nameAlias) {
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
			for(NameAliasV2 name : this.nameAlias) {
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
			for(NameAliasV2 name : this.nameAlias) {
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
	
	public int getPersonUID() {
		Integer personId = new Integer(personUID);
		return personId.intValue();
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
