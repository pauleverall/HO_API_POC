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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "homeofficev1.person")
@JsonInclude(Include.NON_EMPTY)
public class Person{

	@Id
	@GeneratedValue
	@Column(name = "Person_UID")
	@JsonProperty("person_uid")
    private String personUID;
	
	@Column(name = "PNC_ID", nullable = false)
	@JsonProperty("pnc_id")
	private String pncId;
	
	@Column(name = "Gender", nullable = false)
	@JsonProperty("gender")
    private String gender;
	
	@Column(name = "Occupation", nullable = true)
	@JsonProperty("occupation")
    private int occupation;
	
	@Column(name = "Marital_Status", nullable = false)
	@JsonProperty("marital_status")
    private String maritalStatus;
	
	@Column(name = "Wanted_Missing", nullable = false)
	@JsonProperty("wanted_missing_flag")
    private boolean wantedMissing;
	
	@Column(name = "Disqualified_Driver", nullable = false)
	@JsonProperty("dis_driver_flag")
    private boolean disqualifiedDriver;
	
	@Column(name = "Impending_Prosecutions", nullable = false)
	@JsonProperty("im_prosec_flag")
    private boolean impendingProsecutions;
	
	@Column(name = "Convictions", nullable = false)
	@JsonProperty("conviction_flag")
    private boolean convictions;
	
	@Column(name = "Information_Marker", nullable = true)
	@JsonProperty("info_markers")
    private String formationMarker;
	
	@OneToOne
    @JoinColumn(name="Person_UID", nullable=false,insertable=false, updatable=false)
	private Location address;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Person_UID", insertable=false, updatable=false)
	@ElementCollection(targetClass=NameAlias.class)
	@JsonIgnore
	private Set<NameAlias> nameAlias;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Person_UID", insertable=false, updatable=false)
	@ElementCollection(targetClass=PersonNationality.class)
	@JsonIgnore
	private Set<PersonNationality> nationality;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Person_UID", insertable=false, updatable=false)
	@ElementCollection(targetClass=AliasDOB.class)
	@JsonIgnore
	private Set<AliasDOB> aliasDOB;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Person_UID", insertable=false, updatable=false)
	@ElementCollection(targetClass=Identifier.class)
	@JsonIgnore
	private Set<Identifier> identifier;
	
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

	public Set<Identifier> getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Set<Identifier> identifier) {
		this.identifier = identifier;
	}
	
	@JsonProperty("cro_no")
	public String getCroNo() {
		if(this.identifier!=null && this.identifier.size()>0)
		{			
			for(Identifier ident : this.identifier) {
				if(StringUtils.isNotBlank(ident.getIdentifierType()) &&  ident.getIdentifierType().equals("C")) {
					return ident.getIdentifier();
				}
			}
		}
		return null;
	}
	
	@JsonProperty("driver_no")
	public String getDriverNo() {
		if(this.identifier!=null && this.identifier.size()>0)
		{			
			for(Identifier ident : this.identifier) {
				if(StringUtils.isNotBlank(ident.getIdentifierType()) &&  ident.getIdentifierType().equals("D")) {
					return ident.getIdentifier();
				}
			}
		}
		return null;
	}
	
	@JsonProperty("ni_number")
	public String getNiNumber() {
		if(this.identifier!=null && this.identifier.size()>0)
		{			
			for(Identifier ident : this.identifier) {
				if(StringUtils.isNotBlank(ident.getIdentifierType()) &&  ident.getIdentifierType().equals("N")) {
					return ident.getIdentifier();
				}
			}
		}
		return null;
	}
	
	@JsonProperty("passport_no")
	public String getPassportNumber() {
		if(this.identifier!=null && this.identifier.size()>0)
		{			
			for(Identifier ident : this.identifier) {
				if(StringUtils.isNotBlank(ident.getIdentifierType()) &&  ident.getIdentifierType().equals("P")) {
					return ident.getIdentifier();
				}
			}
		}
		return null;
	}

	public Set<AliasDOB> getAliasDOB() {
		return aliasDOB;
	}

	public void setAliasDOB(Set<AliasDOB> aliasDOB) {
		this.aliasDOB = aliasDOB;
	}
	
	@JsonProperty("alias_dates_of_birth")
	public List<Date> getAliasDateOfBirth(){
		if(this.aliasDOB!=null && this.aliasDOB.size()>0)
		{			
			List<Date> aliasDOBs = new ArrayList<Date>();
			for(AliasDOB dob : this.aliasDOB) {
				if(dob.getDateOfBirth() !=null) {
					aliasDOBs.add(dob.getDateOfBirth());
				}
			}
			return aliasDOBs;
		}
		return null;
	}

	public Set<PersonNationality> getNationality() {
		return nationality;
	}

	public void setNationality(Set<PersonNationality> nationality) {
		this.nationality = nationality;
	}
	
	@JsonProperty("nationality")
	public List<String> getPersonNationality(){
		if(this.nationality!=null && this.nationality.size()>0)
		{			
			List<String> personNationalities = new ArrayList<String>();
			for(PersonNationality nationality : this.nationality) {
				if(StringUtils.isNotBlank(nationality.getNationalityUID())) {
					personNationalities.add(nationality.getNationalityUID());
				}
			}
			return personNationalities;
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
	
	@JsonProperty("aliases")
	public List<String> getAliases () {
		if(this.nameAlias!=null && this.nameAlias.size()>0)
		{			
			for(NameAlias name : this.nameAlias) {
				if(StringUtils.isNotBlank(name.getNameType()) &&  name.getNameType().equals("A")) {
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

	public String getPncId() {
		return pncId;
	}

	public void setPncId(String pncId) {
		this.pncId = pncId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getOccupation() {
		return occupation;
	}

	public void setOccupation(int occupation) {
		this.occupation = occupation;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public boolean isWantedMissing() {
		return wantedMissing;
	}

	public void setWantedMissing(boolean wantedMissing) {
		this.wantedMissing = wantedMissing;
	}

	public boolean isDisqualifiedDriver() {
		return disqualifiedDriver;
	}

	public void setDisqualifiedDriver(boolean disqualifiedDriver) {
		this.disqualifiedDriver = disqualifiedDriver;
	}

	public boolean isImpendingProsecutions() {
		return impendingProsecutions;
	}

	public void setImpendingProsecutions(boolean impendingProsecutions) {
		this.impendingProsecutions = impendingProsecutions;
	}

	public boolean isConvictions() {
		return convictions;
	}

	public void setConvictions(boolean convictions) {
		this.convictions = convictions;
	}

	public Location getAddress() {
		return address;
	}

	public void setAddress(Location address) {
		this.address = address;
	}

	public String getFormationMarker() {
		return formationMarker;
	}

	public void setFormationMarker(String formationMarker) {
		this.formationMarker = formationMarker;
	}
	
	
	
}
