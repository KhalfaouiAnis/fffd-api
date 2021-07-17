package com.tekup.restapi.restapi.services;

import com.tekup.restapi.restapi.models.User;

public interface IUserService {
	public User findByEmail(String email);
	public User findByFirstName(String firstName);
	
	public User register(User user);
	public void deleteUser(User user);
	
	public void save(User user);
}
