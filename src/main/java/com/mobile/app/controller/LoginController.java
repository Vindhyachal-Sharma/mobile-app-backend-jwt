package com.mobile.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Admin;
import com.mobile.app.entity.Customer;
import com.mobile.app.entity.LoginCredentials;
import com.mobile.app.entity.User;
import com.mobile.app.exception.UserNotFoundException;
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

	HttpSession session;
	User user;

	@PostMapping("/login")
	public User login(@Valid @RequestBody LoginCredentials credentials, HttpServletRequest request,
			HttpServletResponse response) throws UserNotFoundException {

		user = userRepository.findByUserName(credentials.getUserName());
		if (user == null) {

			throw new UserNotFoundException("Invalid Username");
		}
		if (!user.getPassword().equals(credentials.getPassword())) {

			throw new UserNotFoundException("Invalid Password");
		}
		session = request.getSession();
		session.setAttribute("name", user.getUserName());
		session.setAttribute("role", user.getRole());

		User newUser = user;
		if (user.getRole() == "customer") {
			Customer customer = customerRepository.findByUserName(credentials.getUserName());
			newUser = customer;
		}
		if (user.getRole() == "Admin") {
			Admin admin = adminRepository.findByUserName(credentials.getUserName());
			newUser = admin;
		}

		return newUser;

	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) throws UserNotFoundException {
//		 session = request.getSession();
		if (session != request.getSession() || session == null) {
			throw new UserNotFoundException("User not logged in");
		}
		session.invalidate();
		return "User successfully logged out";
	}
}
