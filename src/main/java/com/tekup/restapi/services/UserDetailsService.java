package com.tekup.restapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekup.restapi.models.MainUserDetails;
import com.tekup.restapi.models.User;
import com.tekup.restapi.repositories.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	UserRepository repo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found !"));
		/*
		 * User user = repo.findByEmail(email).get(); if(user == null) { throw new
		 * UsernameNotFoundException("User not found !"); }
		 */
		return new MainUserDetails(user);
	}

}
