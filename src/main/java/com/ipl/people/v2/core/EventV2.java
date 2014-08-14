package com.ipl.people.v2.core;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "homeofficev2.event")
@JsonInclude(Include.NON_EMPTY)
@NamedQueries({
		@NamedQuery(name = "com.ipl.people.event.findByPersonIdV2", query = "SELECT p FROM EventV2 p WHERE p.personUID = :personId"),
		@NamedQuery(name = "com.ipl.people.event.findByPersonIdAndTypeV2", query = "SELECT p FROM EventV2 p WHERE p.personUID = :personId AND p.eventType LIKE :type"),
		@NamedQuery(name = "com.ipl.people.event.findByPersonIdAndEventIdV2", query = "SELECT p FROM EventV2 p WHERE p.personUID = :personId AND p.eventUID = :eventId") })
public class EventV2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Event_UID")
	@JsonProperty("event_id")
	private int eventUID;

	@Column(name = "Person_UID", nullable = false)
	@JsonProperty("subject")
	private String personUID;

	@Column(name = "Event_Date", nullable = false)
	@JsonProperty("event_date")
	private Date eventDate;

	@Column(name = "Event_Type", nullable = false)
	@JsonProperty("type")
	private String eventType;

	@Column(name = "End_Date", nullable = false)
	@JsonProperty("end")
	private Date endDate;

	@Column(name = "Force_Code", nullable = false)
	@JsonProperty("force_code")
	private String forceCode;

	@Column(name = "Force_Reference", nullable = false)
	@JsonProperty("force_reference")
	private String forceReference;

	@Column(name = "Offence", nullable = false)
	@JsonProperty("offence")
	private String offence;

	@Column(name = "Power_of_Arrest", nullable = false)
	@JsonProperty("power_of_arrest")
	private String powerOfArrest;

	@Column(name = "Report_Date", nullable = false)
	@JsonProperty("report_date")
	private Date reportDate;

	public int getEventUID() {
		return eventUID;
	}

	public void setEventUID(int eventUID) {
		this.eventUID = eventUID;
	}

	public String getPersonUID() {
		return personUID;
	}

	public void setPersonUID(String personUID) {
		this.personUID = personUID;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getForceCode() {
		return forceCode;
	}

	public void setForceCode(String forceCode) {
		this.forceCode = forceCode;
	}

	public String getForceReference() {
		return forceReference;
	}

	public void setForceReference(String forceReference) {
		this.forceReference = forceReference;
	}

	public String getOffence() {
		return offence;
	}

	public void setOffence(String offence) {
		this.offence = offence;
	}

	public String getPowerOfArrest() {
		return powerOfArrest;
	}

	public void setPowerOfArrest(String powerOfArrest) {
		this.powerOfArrest = powerOfArrest;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

}
