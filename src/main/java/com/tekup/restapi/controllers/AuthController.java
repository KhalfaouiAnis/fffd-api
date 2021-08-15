package com.tekup.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.tekup.restapi.models.JwtRequest;
import com.tekup.restapi.models.JwtResponse;
import com.tekup.restapi.models.RegisterForm;
import com.tekup.restapi.models.User;
import com.tekup.restapi.services.IUserService;
import com.tekup.restapi.utility.JWTUtility;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private IUserService userService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private AuthenticationManager authManager;

	@ResponseBody
	@PostMapping("/register")
	public ResponseEntity<JSONObject> processRegistration(@RequestBody RegisterForm user) {

		Map<String, String> responseMap = new LinkedHashMap<>();

		// check if the email is already used
		User userExists = userService.findByEmail(user.getEmail());

		if (userExists != null) {
			responseMap.put("message", "Email already used !");
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
		}
		System.out.println("user.getRoles(): " + user.getRoles());
		List<String> rolesAsString = new ArrayList<String>();
		user.getRoles().forEach(role -> rolesAsString.add(role.getRoleName()));

		userService.register(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
				rolesAsString);
		responseMap.put("message", "Account created successfully");
		JSONObject responseObject = new JSONObject(responseMap);

		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}

	@PostMapping("/authenticate")
	@CrossOrigin("*")
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid credentials !", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
		final String token = jwtUtility.generateToken(userDetails);
		final User user = userService.findByEmail(jwtRequest.getEmail());
		return new JwtResponse(user, token);

	}

}
