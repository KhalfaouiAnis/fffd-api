package com.tekup.restapi.models;

import org.springframework.security.core.userdetails.UserDetails;

public class AuthResponse {
	private String message;
	private UserDetails userDetails;
	private String jwt;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
}
