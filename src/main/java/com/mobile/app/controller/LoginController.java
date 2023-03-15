package com.mobile.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Admin;
import com.mobile.app.entity.Customer;
import com.mobile.app.entity.LoginCredentials;
import com.mobile.app.entity.User;
import com.mobile.app.exception.UserNotFoundException;
import com.mobile.app.jwt.JwtUtil;
import com.mobile.app.repository.AdminRepository;
import com.mobile.app.repository.CustomerRepository;
import com.mobile.app.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class LoginController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	private JwtUtil jwtUtil;


	User user;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginCredentials credentials, HttpServletRequest request,
			HttpServletResponse response) throws UserNotFoundException {

		user = userRepository.findByUserName(credentials.getUserName());
		if (user == null) {

			throw new UserNotFoundException("Invalid Username");
		}
		if (!user.getPassword().equals(credentials.getPassword())) {

			throw new UserNotFoundException("Invalid Password");
		}
		User newUser = user;
		if (user.getRole()=="customer") {
			Customer customer = customerRepository.findByUserName(credentials.getUserName());
			newUser = customer;
		}
		if (user.getRole()=="Admin") {
			Admin admin = adminRepository.findByUserName(credentials.getUserName());
			newUser = admin;
		}

		String token = jwtUtil.generateToken(user);
		response.addHeader("Authorization", "Bearer "+token);
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
		return ResponseEntity.ok().body(newUser) ;

	}

}
