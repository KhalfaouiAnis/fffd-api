package com.tekup.restapi.app.models;

public class JwtResponse {
	private User user;
	private String token;

	public JwtResponse(User user, String token) {
		this.user = user;
		this.token = token;
	}

	public JwtResponse() {

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/*
	 * private Collection<? extends GrantedAuthority> authorities; public
	 * Collection<? extends GrantedAuthority> getAuthorities() { return authorities;
	 * }
	 * 
	 * public void setAuthorities(Collection<? extends GrantedAuthority>
	 * authorities) { this.authorities = authorities; }
	 * 
	 */
}
