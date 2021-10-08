package com.tekup.restapi.app.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "address")
public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String street;
	private int postalCode;
	private String country;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	@JsonManagedReference
	private Location location;

	@OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
	@JsonBackReference
	private Restaurant restaurant;

	public Address(String street, int postalCode, String country, Location location) {
		this.street = street;
		this.postalCode = postalCode;
		this.country = country;
		this.location = location;
	}
	
	public Address(String street, int postalCode, String country) {
		this.street = street;
		this.postalCode = postalCode;
		this.country = country;
	}
	
	public Address(String country, String street) {
		this.street = street;
		this.country = country;
	}

	public Address() {
	}

	public int getId() {
		return id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", postalCode=" + postalCode + ", country=" + country + ", location="
				+ location + "]";
	}
}
