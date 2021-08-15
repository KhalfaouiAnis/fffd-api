package com.tekup.restapi.services;

import java.util.List;

import com.tekup.restapi.models.User;

public interface IUserService {
	public User findByEmail(String email);

	public User findByFirstName(String firstName);

	public User register(String firstName, String lastName, String email, String password, List<String> userRoles);

	public void deleteUser(User user);

	public void save(User user);
}