package com.ipl.people.authentication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "homeofficev2.guid")
public class Authentication {
	
	@Id
	@Column(name = "GUID")
    private String guid;
	
	@Column(name = "Organisation" , nullable = true)
    private String organisation;
	
	@Column(name = "Point_Of_Contact" , nullable = true)
    private String pointOfContact;
	
	@Column(name = "Contact_Number" , nullable = true)
    private String contactNumber;
	
	@Column(name = "Blocked" , nullable = true)
    private boolean blocked;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getPointOfContact() {
		return pointOfContact;
	}

	public void setPointOfContact(String pointOfContact) {
		this.pointOfContact = pointOfContact;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	

}
