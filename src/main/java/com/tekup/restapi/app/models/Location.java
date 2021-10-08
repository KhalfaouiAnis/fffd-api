package com.tekup.restapi.app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "location")
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String altitude;
	private String longitude;
	
	@OneToOne(mappedBy = "location")
	@JsonBackReference
	private Address address;

	public Location(String altitude, String longitude) {
		this.altitude = altitude;
		this.longitude = longitude;
	}

	public Location() {

	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Location [altitude=" + altitude + ", longitude=" + longitude + "]";
	}

}
