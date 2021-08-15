package com.tekup.restapi.models;

import java.util.Set;

public class RegisterForm {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Set<Role> roles;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public RegisterForm(String firstName, String lastName, String email, String password, Set<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	public RegisterForm(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public RegisterForm() {
		
	}
	@Override
	public String toString() {
		return "RegisterForm [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", roles=" + roles + "]";
	}
	
	
}