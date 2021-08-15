package com.tekup.restapi.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.restapi.models.AuthResponse;
import com.tekup.restapi.models.Role;
import com.tekup.restapi.models.User;
import com.tekup.restapi.repositories.RoleRepository;
import com.tekup.restapi.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

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
	public User register(String firstName, String lastName, String email, String password, List<String> userRoles) {
		System.out.println("userRoles: " + userRoles);
		User newUser = new User(firstName, lastName, email, password);
		newUser.setCreationDate(new Date());
		newUser.setPassword(bCryptPasswordEncoder.encode(password));
		List<Role> roles = new ArrayList<>();
		if (!userRoles.isEmpty()) {
			userRoles.forEach(role -> {
				switch (role) {
				case "ADMIN":
					Role adminRole = roleRepository.findByRoleName("ADMIN")
							.orElseThrow(() -> new RuntimeException("Failed! -> Cause: User Role not find."));
					roles.add(adminRole);
					break;
				case "RESTAURANT_MANAGER":
					Role restoMngrRole = roleRepository.findByRoleName("RESTAURANT_MANAGER")
							.orElseThrow(() -> new RuntimeException("Failed! -> Cause: User Role not find."));
					roles.add(restoMngrRole);
					break;
				case "DELIVERY_MAN":
					Role deliveryManRole = roleRepository.findByRoleName("DELIVERY_MAN")
							.orElseThrow(() -> new RuntimeException("Failed! -> Cause: User Role not find."));
					roles.add(deliveryManRole);
					break;
				case "CLIENT":
					Role clientRole = roleRepository.findByRoleName("CLIENT")
							.orElseThrow(() -> new RuntimeException("Failed! -> Cause: User Role not find."));
					roles.add(clientRole);
					break;
				default:
					break;
				}
			});
		} else {
			Role clientRoleDefault = roleRepository.findByRoleName("CLIENT")
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			roles.add(clientRoleDefault);
		}
		newUser.setRoles(roles);
		userRepository.save(newUser);
		return newUser;
	}

	@Override
	public void save(User user) {
		User newUser = new User(user.getEmail(), user.getPassword());
		newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(newUser);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

}
