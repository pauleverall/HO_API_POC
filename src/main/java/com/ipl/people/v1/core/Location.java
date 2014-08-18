package com.ipl.people.v1.core;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model mapping Address table.
 * 
 * @author Asha
 *
 */
@Entity
@Table(name = "homeofficev1.location")
@JsonInclude(Include.NON_EMPTY) 
public class Location {

	@Id
	@GeneratedValue
	@Column(name = "Location_UID")
	@JsonIgnore
    private String locationUID;
    
    @Column(name = "Person_UID" , nullable = false)
    @JsonIgnore
    private String personUID;
    
    @Column(name = "PO_Box" , nullable = true)
   	@JsonProperty("po_box") 
    private String poBox;
    
    @Column(name = "Extended_Address" , nullable = true)
   	@JsonProperty("extended_address") 
    private String extendedAddress;
    
    @Column(name = "Street_Address1" , nullable = true)
   	@JsonProperty("street_address1")  
    private String streetAddress1;
    
    @Column(name = "Street_Address2" , nullable = true)
   	@JsonProperty("street_address2") 
    private String streetAddress2;
    
    @Column(name = "Street_Address3" , nullable = true)
   	@JsonProperty("street_address3") 
    private String streetAddress3;
    
    @Column(name = "Locality" , nullable = true)
   	@JsonProperty("locality") 
    private String locality;
    
    @Column(name = "Region" , nullable = true)
   	@JsonProperty("region") 
    private String region;
    
    @Column(name = "Postal_Code" , nullable = true)
   	@JsonProperty("postal_code") 
    private String postalCode;
    
    @Column(name = "Country_Name" , nullable = true)
   	@JsonProperty("country_name") 
    private String countryName;
    
    @Column(name = "Notes" , nullable = true)
   	@JsonProperty("notes") 
    private String notes;
    
    @Column(name = "Address_Date" , nullable = true)
   	@JsonProperty("address_date") 
    private Date addressDate;
    
    @Column(name = "Address_Force_Code" , nullable = true)
   	@JsonProperty("address_force_code") 
    private String addressForceCode;

	public String getLocationUID() {
		return locationUID;
	}

	public void setLocationUID(String locationUID) {
		this.locationUID = locationUID;
	}

	public String getPersonUID() {
		return personUID;
	}

	public void setPersonUID(String personUID) {
		this.personUID = personUID;
	}

	public String getPoBox() {
		return poBox;
	}

	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}

	public String getExtendedAddress() {
		return extendedAddress;
	}

	public void setExtendedAddress(String extendedAddress) {
		this.extendedAddress = extendedAddress;
	}

	public String getStreetAddress1() {
		return streetAddress1;
	}

	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
	}

	public String getStreetAddress2() {
		return streetAddress2;
	}

	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}

	public String getStreetAddress3() {
		return streetAddress3;
	}

	public void setStreetAddress3(String streetAddress3) {
		this.streetAddress3 = streetAddress3;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getAddressDate() {
		return addressDate;
	}

	public void setAddressDate(Date addressDate) {
		this.addressDate = addressDate;
	}

	public String getAddressForceCode() {
		return addressForceCode;
	}

	public void setAddressForceCode(String addressForceCode) {
		this.addressForceCode = addressForceCode;
	}

}
