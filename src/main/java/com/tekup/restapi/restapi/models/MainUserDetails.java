package com.tekup.restapi.restapi.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MainUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private User user;


	public MainUserDetails(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName().toString()));
		}
		return authorities;
	}

	public int getId() {
		return user.getId();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
