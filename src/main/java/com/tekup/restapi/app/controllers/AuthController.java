package com.tekup.restapi.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.restapi.app.models.JwtRequest;
import com.tekup.restapi.app.models.JwtResponse;
import com.tekup.restapi.app.models.User;
import com.tekup.restapi.app.services.IUserService;
import com.tekup.restapi.app.utility.JWTUtility;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;

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
	@PostMapping(value = "/register")
	public ResponseEntity<JSONObject> processRegistration(@RequestBody User user) {
		Map<String, String> responseMap = new LinkedHashMap<>();

		try {
			User userExists = userService.findByEmail(user.getEmail());

			if (userExists != null) {
				responseMap.put("message", "Email already used !");
				JSONObject responseObject = new JSONObject(responseMap);
				return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
			}

			userService.register(user);
			responseMap.put("message", "Account created successfully");
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<>(responseObject, HttpStatus.OK);

		} catch (Exception e) {
			responseMap.put("message", e.getMessage());
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
		Map<String, String> responseMap = new LinkedHashMap<>();
		try {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
		} catch (Exception e) {
			responseMap.put("message", "Bad credentials !");
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<JSONObject>(responseObject, HttpStatus.BAD_REQUEST);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
		final String token = jwtUtility.generateToken(userDetails);
		final User user = userService.findByEmail(jwtRequest.getEmail());
		return new ResponseEntity<>(new JwtResponse(user, token), HttpStatus.OK);
	}

}
