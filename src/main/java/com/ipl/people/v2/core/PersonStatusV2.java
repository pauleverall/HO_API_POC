package com.ipl.people.v2.core;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "homeofficev2.person_status")
@JsonInclude(Include.NON_EMPTY)
public class PersonStatusV2 {

	private final static String eventUrl1 = "/v2/people/";
	private final static String eventUrl2 = "/events/";;

	@Id
	@GeneratedValue
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
	private String relatedEventUID;

	@Column(name = "Status_Record_Date", nullable = true)
	@JsonProperty("timestamp")
	private Date statusRecordDate;

	@Column(name = "Status_Expiry_Date", nullable = true)
	private Date statusExpiryDate;

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

	public String getRelatedEventUID() {
		return relatedEventUID;
	}

	public void setRelatedEventUID(String relatedEventUID) {
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

	@JsonProperty("name")
	public String getName() {
		return this.status.getDescription();
	}

	@JsonProperty("associated_event")
	public String getAssociatedEvent() {
		return eventUrl1 + this.personUID + eventUrl2 + this.relatedEventUID;
	}

}
