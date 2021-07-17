package com.tekup.restapi.restapi.services;

import java.util.Date;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.restapi.restapi.models.User;
import com.tekup.restapi.restapi.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	UserRepository userRepository;
	
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User register(User user) {
		User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
		newUser.setCreationDate(new Date());
		newUser.setRole("client");
		System.out.println("password: "+bCryptPasswordEncoder.encode(user.getPassword()));
		newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(newUser);
		return newUser;
	}

	@Override
	public void save(User user) {
		User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
		newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(newUser);		
	}

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

}
