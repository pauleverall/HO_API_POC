package com.ipl.people.v2.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "homeofficev2.statuses")
public class StatusesV2 {

	@Id
	@GeneratedValue
	@Column(name = "Status_UID")
    private String statusUID;
	
	@Column(name = "Description" , nullable = false)
    private String description;

	public String getStatusUID() {
		return statusUID;
	}

	public void setStatusUID(String statusUID) {
		this.statusUID = statusUID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
