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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model mapping Person table with all the required fields for Get Person API.
 * 
 * @author Asha
 *
 */
@Entity
@Table(name = "homeofficev2.person")
@JsonInclude(Include.NON_EMPTY)
@JsonFilter("fieldsFilter")
public class PersonV2 {

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
	private String occupation;

	@Column(name = "Marital_Status", nullable = false)
	@JsonProperty("marital_status")
	private String maritalStatus;

	@Column(name = "Photograph_URL", nullable = true)
	@JsonProperty("photograph_url")
	private String photographURL;

	@OneToOne
	@JoinColumn(name = "Person_UID", nullable = false, insertable = false, updatable = false)
	private LocationV2 address;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Person_UID", insertable = false, updatable = false)
	@ElementCollection(targetClass = NameAliasV2.class)
	@JsonIgnore
	private Set<NameAliasV2> nameAlias;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Person_UID", insertable = false, updatable = false)
	@ElementCollection(targetClass = PersonNationalityV2.class)
	@JsonIgnore
	private Set<PersonNationalityV2> nationality;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Person_UID", insertable = false, updatable = false)
	@ElementCollection(targetClass = AliasDOBV2.class)
	@JsonIgnore
	private Set<AliasDOBV2> aliasDOB;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Person_UID", insertable = false, updatable = false)
	@ElementCollection(targetClass = IdentifierV2.class)
	@JsonIgnore
	private Set<IdentifierV2> identifier;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Person_UID", insertable = false, updatable = false)
	@ElementCollection(targetClass = DuplicatePersonV2.class)
	@JsonIgnore
	private Set<DuplicatePersonV2> duplicatePerson;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Person_UID", insertable = false, updatable = false)
	@ElementCollection(targetClass = PersonStatusV2.class)
	@JsonProperty("status")
	private Set<PersonStatusV2> personStatuses;

	public Set<DuplicatePersonV2> getDuplicatePerson() {
		return duplicatePerson;
	}

	public void setDuplicatePerson(Set<DuplicatePersonV2> duplicatePerson) {
		this.duplicatePerson = duplicatePerson;
	}

	@JsonProperty("related_records")
	public List<String> getRelatedRecords() {
		if (this.duplicatePerson != null && this.duplicatePerson.size() > 0) {
			List<String> relatedRecords = new ArrayList<String>();
			for (DuplicatePersonV2 record : this.duplicatePerson) {
				StringBuilder relatedRecord = new StringBuilder("");
				if (StringUtils.isNotBlank(record.getRelationship())) {
					relatedRecord.append(record.getRelationship() + ":");
				}
				if (StringUtils.isNotBlank(record.getRelatedUID())) {
					relatedRecord.append(record.getRelatedUID());
				}
				relatedRecords.add(relatedRecord.toString());
			}
			return relatedRecords;
		}
		return null;
	}

	public Set<IdentifierV2> getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Set<IdentifierV2> identifier) {
		this.identifier = identifier;
	}

	@JsonProperty("cro_no")
	public String getCroNo() {
		if (this.identifier != null && this.identifier.size() > 0) {
			for (IdentifierV2 ident : this.identifier) {
				if (StringUtils.isNotBlank(ident.getIdentifierType())
						&& ident.getIdentifierType().equals("C")) {
					return ident.getIdentifier();
				}
			}
		}
		return null;
	}

	@JsonProperty("driver_no")
	public String getDriverNo() {
		if (this.identifier != null && this.identifier.size() > 0) {
			for (IdentifierV2 ident : this.identifier) {
				if (StringUtils.isNotBlank(ident.getIdentifierType())
						&& ident.getIdentifierType().equals("D")) {
					return ident.getIdentifier();
				}
			}
		}
		return null;
	}

	@JsonProperty("ni_number")
	public String getNiNumber() {
		if (this.identifier != null && this.identifier.size() > 0) {
			for (IdentifierV2 ident : this.identifier) {
				if (StringUtils.isNotBlank(ident.getIdentifierType())
						&& ident.getIdentifierType().equals("N")) {
					return ident.getIdentifier();
				}
			}
		}
		return null;
	}

	@JsonProperty("passport_no")
	public String getPassportNumber() {
		if (this.identifier != null && this.identifier.size() > 0) {
			for (IdentifierV2 ident : this.identifier) {
				if (StringUtils.isNotBlank(ident.getIdentifierType())
						&& ident.getIdentifierType().equals("P")) {
					return ident.getIdentifier();
				}
			}
		}
		return null;
	}

	public Set<AliasDOBV2> getAliasDOB() {
		return aliasDOB;
	}

	public void setAliasDOB(Set<AliasDOBV2> aliasDOB) {
		this.aliasDOB = aliasDOB;
	}

	@JsonProperty("alias_dates_of_birth")
	public List<Date> getAliasDateOfBirth() {
		if (this.aliasDOB != null && this.aliasDOB.size() > 0) {
			List<Date> aliasDOBs = new ArrayList<Date>();
			for (AliasDOBV2 dob : this.aliasDOB) {
				if (dob.getDateOfBirth() != null) {
					aliasDOBs.add(dob.getDateOfBirth());
				}
			}
			return aliasDOBs;
		}
		return null;
	}

	public Set<PersonNationalityV2> getNationality() {
		return nationality;
	}

	public void setNationality(Set<PersonNationalityV2> nationality) {
		this.nationality = nationality;
	}

	@JsonProperty("nationality")
	public List<String> getPersonNationality() {
		if (this.nationality != null && this.nationality.size() > 0) {
			List<String> personNationalities = new ArrayList<String>();
			for (PersonNationalityV2 nationality : this.nationality) {
				if (StringUtils.isNotBlank(nationality.getNationalityUID())) {
					personNationalities.add(nationality.getNationalityUID());
				}
			}
			return personNationalities;
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
		if (this.nameAlias != null && this.nameAlias.size() > 0) {
			for (NameAliasV2 name : this.nameAlias) {
				if (StringUtils.isNotBlank(name.getNameType())
						&& name.getNameType().equals("P")) {
					return name.getPlaceOfBirth();
				}
			}
		}
		return null;
	}

	@JsonProperty("date_of_birth")
	public Date getDateOfBirth() {
		if (this.nameAlias != null && this.nameAlias.size() > 0) {
			for (NameAliasV2 name : this.nameAlias) {
				if (StringUtils.isNotBlank(name.getNameType())
						&& name.getNameType().equals("P")) {
					return name.getDateOfBirth();
				}
			}
		}
		return null;
	}

	@JsonProperty("surname")
	public String getSurname() {
		if (this.nameAlias != null && this.nameAlias.size() > 0) {
			for (NameAliasV2 name : this.nameAlias) {
				if (StringUtils.isNotBlank(name.getNameType())
						&& name.getNameType().equals("P")) {
					return name.getSurname();
				}
			}
		}
		return null;
	}

	@JsonProperty("given_name")
	public List<String> getGivenNames() {
		if (this.nameAlias != null && this.nameAlias.size() > 0) {
			for (NameAliasV2 name : this.nameAlias) {
				if (StringUtils.isNotBlank(name.getNameType())
						&& name.getNameType().equals("P")) {
					List<String> names = new ArrayList<String>();
					if (StringUtils.isNotBlank(name.getForename1())) {
						names.add(name.getForename1());
					}
					if (StringUtils.isNotBlank(name.getForename2())) {
						names.add(name.getForename2());
					}
					if (StringUtils.isNotBlank(name.getForename3())) {
						names.add(name.getForename3());
					}
					if (StringUtils.isNotBlank(name.getForename4())) {
						names.add(name.getForename4());
					}
					return names;
				}
			}
		}
		return null;
	}

	@JsonProperty("aliases")
	public List<String> getAliases() {
		if (this.nameAlias != null && this.nameAlias.size() > 0) {
			for (NameAliasV2 name : this.nameAlias) {
				if (StringUtils.isNotBlank(name.getNameType())
						&& name.getNameType().equals("A")) {
					List<String> names = new ArrayList<String>();
					if (StringUtils.isNotBlank(name.getForename1())) {
						names.add(name.getForename1());
					}
					if (StringUtils.isNotBlank(name.getForename2())) {
						names.add(name.getForename2());
					}
					if (StringUtils.isNotBlank(name.getForename3())) {
						names.add(name.getForename3());
					}
					if (StringUtils.isNotBlank(name.getForename4())) {
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

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public LocationV2 getAddress() {
		return address;
	}

	public void setAddress(LocationV2 address) {
		this.address = address;
	}

	public Set<PersonStatusV2> getPersonStatuses() {
		if (personStatuses != null && personStatuses.size() > 0) {
			return personStatuses;
		}
		return null;
	}

	public void setPersonStatuses(Set<PersonStatusV2> personStatuses) {
		this.personStatuses = personStatuses;
	}

	public String getPhotographURL() {
		return photographURL;
	}

	public void setPhotographURL(String photographURL) {
		this.photographURL = photographURL;
	}

}
