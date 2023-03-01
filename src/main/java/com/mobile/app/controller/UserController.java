package com.mobile.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.User;
import com.mobile.app.exception.UserNotFoundException;
import com.mobile.app.repository.UserRepository;
import com.mobile.app.service.UserServiceImpl;
@RestController
@CrossOrigin("*")
public class UserController {
	@Autowired
	UserServiceImpl userService;

	@Autowired
	UserRepository userRepository;

	@GetMapping(value = "/signin")
	public ResponseEntity<String> signIn(@RequestBody User user, HttpServletRequest request) {
		
		try {

			userService.signIn(user);
			return new ResponseEntity<String>("Login Successfull!", HttpStatus.OK);
		} catch (UserNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		return new ResponseEntity<String>("Login Unsuccessfull!", HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/getUser/{uid}")

	public ResponseEntity<?> getUser(@PathVariable("uid") Integer id, HttpServletRequest request) throws UserNotFoundException {
		

		User user = userService.getUserById(id);
		if (user == null) {
			throw new UserNotFoundException("Appointment with appointment id:" + id + "not found");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("signout")
	public ResponseEntity<String> signOut(HttpServletRequest request) {
		

		
		return new ResponseEntity<String>("Logout Successfull!", HttpStatus.OK);

	}


	@PatchMapping("/{id}/{userName}/{password}")
	public ResponseEntity<?> updateCredentials(@PathVariable("id") Integer userId, @PathVariable("userName") String userName,
			@PathVariable("password") String password,HttpServletRequest request) throws UserNotFoundException {
		User user = checkUserLoggedIn(request);
		if(!user.getRole().equals("admin"))
			throw new UserNotFoundException("Invalid Operation");
		
		User userToBeUpdated = userService.getUserById(userId);

		User updatedUser = userService.updateCredentials(userToBeUpdated, userName, password);
		if (updatedUser != null) {
			return new ResponseEntity<String>("Credentials updated successfully", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("Not able to update credentials", HttpStatus.NOT_FOUND);

	}
	
	public User checkUserLoggedIn(HttpServletRequest request) throws UserNotFoundException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			throw new UserNotFoundException("You should login first");
		}
		String userName = (String) session.getAttribute("name");
		User user = userRepository.findByUserName(userName);
		return user;
				
	}

}
