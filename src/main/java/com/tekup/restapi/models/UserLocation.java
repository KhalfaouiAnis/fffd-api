package com.tekup.restapi.models;

public class UserLocation {
	private String altitude;
	private String longitude;

	public UserLocation(String altitude, String longitude) {
		this.altitude = altitude;
		this.longitude = longitude;
	}

	public UserLocation() {

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

	@Override
	public String toString() {
		return "UserLocation [altitude=" + altitude + ", longitude=" + longitude + "]";
	}

}
