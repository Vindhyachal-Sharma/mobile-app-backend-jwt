package com.mobile.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.User;
import com.mobile.app.exception.UserNotFoundException;
import com.mobile.app.jwt.JwtRequest;
import com.mobile.app.jwt.JwtResponse;
import com.mobile.app.jwt.JwtUtil;
import com.mobile.app.repository.UserRepository;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	UserRepository userRepository;

	User user;

	@PostMapping
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest request) throws Exception {

		user = userRepository.findByUserName(request.getUserName());

		if (user == null) {
			throw new UserNotFoundException("User not found with username: " + request.getUserName());
		} 

		if (!(user.getPassword().equals(request.getPassword())))
			throw new UserNotFoundException("Invalid Password");

		String token = jwtUtil.generateToken(user);

		return ResponseEntity.ok(new JwtResponse(token));
		
	}
}
