package com.tekup.restapi.app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.restapi.app.models.Restaurant;
import com.tekup.restapi.app.models.Role;
import com.tekup.restapi.app.models.User;
import com.tekup.restapi.app.repositories.RestaurantRepository;
import com.tekup.restapi.app.repositories.RoleRepository;
import com.tekup.restapi.app.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RestaurantRepository restoRepo;

	@Autowired
	PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public User findByEmail(String email) {
		try {
			return userRepository.findByEmail(email).get();
		} catch (NoSuchElementException e) {
			return null;
		}

	}

	@Override
	public User findByFirstName(String firstName) {
		try {
			return userRepository.findByFirstName(firstName).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public User findById(int userId) {
		try {
			return userRepository.findById(userId).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public User register(User user) {
		User newUser = user;
		newUser.setCreationDate(new Date());
		newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		List<Role> roles = new ArrayList<>();
		if (!user.getRoles().isEmpty()) {
			user.getRoles().forEach(role -> {
				role.setUser(newUser);
				roles.add(role);
			});
		}

		newUser.setRoles(roles);

		Restaurant resto;
		if (user.getRestaurant() != null) {
			resto = restoRepo.findById(user.getRestaurant().getId()).get();
			newUser.setRestaurant(resto);
		}
		userRepository.save(newUser);
		roleRepository.saveAll(roles);
		return newUser;
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public void addRole(int userId, String roleName) {
		User storedUser = userRepository.findById(userId).get();
		Role role = new Role(roleName);
		role.setUser(storedUser);
		storedUser.getRoles().add(role);
		userRepository.save(storedUser);
		roleRepository.save(role);
	}

	@Override
	public void removeRole(int userId, Role role) {
		User storedUser = userRepository.findById(userId).get();
		storedUser.getRoles().remove(role);
		userRepository.save(storedUser);
	}

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public List<User> findByRoleName(String roleName) {
		return userRepository.findByRoleName(roleName).get();
	}

	@Override
	public void update(User user, int userId) {
		User fromDB = userRepository.findById(userId).get();

		if (user.getFirstName() != null) {
			fromDB.setFirstName(user.getFirstName());
		}

		if (user.getLastName() != null) {
			fromDB.setLastName(user.getLastName());
		}

		if (user.getEmail() != null) {
			fromDB.setEmail(user.getEmail());
		}

		if (user.getPhoneNumber() != null) {
			fromDB.setPhoneNumber(user.getPhoneNumber());
		}

		if (user.getAddress() != null) {
			fromDB.setAddress(user.getAddress());
		}

		if (user.getProfileImage() != null) {
			fromDB.setProfileImage(user.getProfileImage());
		}

		userRepository.save(fromDB);
	}

}
