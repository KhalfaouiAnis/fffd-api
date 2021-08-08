package com.tekup.restapi.restapi.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.tekup.restapi.restapi.configuration.JwtProvider;
import com.tekup.restapi.restapi.models.AuthResponse;
import com.tekup.restapi.restapi.models.User;
import com.tekup.restapi.restapi.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder bCryptPasswordEncoder;
	
	/*
	 * @Autowired JwtProvider jwtProvider;
	 */

    //@Autowired
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
	public User register(User user) {
		User newUser = new User(user.getUsername(), user.getEmail());
		newUser.setCreationDate(new Date());
		newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		//newUser.setRoles(new HashSet<Role>(new Role(RoleName.CLIENT)));
		userRepository.save(newUser);
		return newUser;
	}
	
	@Override
	public AuthResponse login(String email, String password) {
        {
			
			  Authentication authentication = null; AuthResponse response = null;
			  
			  authentication = authenticationManager.authenticate( new
			  UsernamePasswordAuthenticationToken(email, password)); response = new
			  AuthResponse();
			  SecurityContextHolder.getContext().setAuthentication(authentication);
			  //String jwt = jwtProvider.generateJwtToken(authentication);
			  UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			  //response.setJwt(jwt); response.setUserDetails(userDetails);
			 

            return response;
        }

	}

	
	  @Override public void save(User user) { User newUser = new
	  User(user.getEmail(), user.getPassword());
	  newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	  userRepository.save(newUser); }
	 

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	/*
	 * @Override public List<User> getUsers() { return userRepository.getAllUsers();
	 * }
	 */

}
