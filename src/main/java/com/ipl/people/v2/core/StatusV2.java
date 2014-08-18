package com.ipl.people.v2.core;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model mapping Person status table with all the required fields to Create a status.
 * 
 * @author Asha
 *
 */
@Entity
@Table(name = "homeofficev2.person_status")
@JsonInclude(Include.NON_EMPTY)
public class StatusV2 {


	@Id
	@GeneratedValue
	@JsonProperty("status_id")
	@Column(name = "ID")
	private int id;

	@Column(name = "Status_UID")
	@JsonProperty("type")
	private String statusUID;

	@Column(name = "Person_UID", nullable = false)
	private String personUID;

	@Column(name = "Reason", nullable = true)
	@JsonProperty("reason")
	private String reason;

	@Column(name = "Status_Record_Date", nullable = true)
	private Date statusRecordDate;

	@Column(name = "Status_Expiry_Date", nullable = true)
	@JsonProperty("expiry_date")
	private Date statusExpiryDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public void setStatusUID(String statusUID) {
		this.statusUID =statusUID;
	}
	public String getStatusUID() {
		return statusUID;
	}

}
