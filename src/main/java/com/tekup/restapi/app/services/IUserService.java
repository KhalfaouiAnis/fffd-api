package com.tekup.restapi.app.services;

import java.util.List;

import com.tekup.restapi.app.models.Role;
import com.tekup.restapi.app.models.User;

public interface IUserService {
	public User findByEmail(String email);

	public User findByFirstName(String firstName);

	public User register(User user);

	public void deleteUser(User user);

	public void save(User user);
	
	void update(User user, int userId);

	public void addRole(int userId, String roleName);

	public void removeRole(int userId, Role role);

	public List<User> getAllUsers();

	public List<User> findByRoleName(String roleName);

	public User findById(int userId);
}