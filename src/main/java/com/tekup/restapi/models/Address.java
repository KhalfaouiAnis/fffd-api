package com.tekup.restapi.models;

public class Address {
	private String street;
	private int postalCode;
	private UserLocation location;

	public Address(String street, int postalCode, UserLocation location) {
		this.street = street;
		this.postalCode = postalCode;
		this.location = location;
	}

	public Address() {
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

	public UserLocation getLocation() {
		return location;
	}

	public void setLocation(UserLocation location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", postalCode=" + postalCode + ", location=" + location + "]";
	}

}
