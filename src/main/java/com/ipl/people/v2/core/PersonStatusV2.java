package com.ipl.people.v2.core;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model mapping Person status table.
 * 
 * @author Asha
 *
 */
@Entity
@Table(name = "homeofficev2.person_status")
@JsonInclude(Include.NON_EMPTY)
@NamedQueries({
	@NamedQuery(name = "com.ipl.people.person.status.findByPersonIdAndStatusIdV2", query = "SELECT s FROM PersonStatusV2 s WHERE s.personUID = :personId and s.statusUID = :statusId") })
public class PersonStatusV2 {

	private final static String eventUrl1 = "/v2/people/";
	private final static String eventUrl2 = "/events/";;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@OneToOne
	@JoinColumn(name = "Status_UID", nullable = false, insertable = false, updatable = false)
	@JsonIgnore
	private StatusesV2 status;

	@Column(name = "Person_UID", nullable = false)
	@JsonIgnore
	private String personUID;

	@Column(name = "Reason", nullable = true)
	@JsonProperty("reason")
	private String reason;

	@Column(name = "Related_Event_UID", nullable = true)
	@JsonIgnore
	private int relatedEventUID;

	@Column(name = "Status_Record_Date", nullable = true)
	@JsonProperty("start_date")
	private Date statusRecordDate;

	@Column(name = "Status_Expiry_Date", nullable = true)
	@JsonIgnore
	private Date statusExpiryDate;
	
	@Column(name = "Status_UID", nullable = true)
	@JsonIgnore
	private String statusUID;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StatusesV2 getStatus() {
		return status;
	}

	public void setStatus(StatusesV2 status) {
		this.status = status;
	}

	public String getPersonUID() {
		return personUID;
	}

	public void setPersonUID(String personUID) {
		this.personUID = personUID;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getRelatedEventUID() {
		return relatedEventUID;
	}

	public void setRelatedEventUID(int relatedEventUID) {
		this.relatedEventUID = relatedEventUID;
	}

	public Date getStatusRecordDate() {
		return statusRecordDate;
	}

	public void setStatusRecordDate(Date statusRecordDate) {
		this.statusRecordDate = statusRecordDate;
	}

	public Date getStatusExpiryDate() {
		return statusExpiryDate;
	}

	public void setStatusExpiryDate(Date statusExpiryDate) {
		this.statusExpiryDate = statusExpiryDate;
	}

	@JsonProperty("type")
	public String getName() {
		return this.status.getDescription();
	}

	@JsonProperty("associated_event")
	public String getAssociatedEvent() {
		if(this.relatedEventUID>0){
			return eventUrl1 + this.personUID + eventUrl2 + this.relatedEventUID;
		}
		return null;
	}

	public String getStatusUID() {
		return statusUID;
	}

	public void setStatusUID(String statusUID) {
		this.statusUID = statusUID;
	}
}
