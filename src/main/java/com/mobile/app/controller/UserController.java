package com.mobile.app.controller;

import org.springframework.web.bind.annotation.RestController;
@RestController
public class UserController {
//	@Autowired
//	UserServiceImpl service;
//
//	@Autowired
//	UserRepository repository;
//
////	@GetMapping(value = "/signin")
////	public ResponseEntity<String> signIn(@RequestBody User user, HttpServletRequest request) {
////		
////		try {
////
////			service.signIn(user);
////			return new ResponseEntity<String>("Login Successfull!", HttpStatus.OK);
////		} catch (UserException ex) {
////			System.out.println(ex.getMessage());
////		}
////		return new ResponseEntity<String>("Login Unsuccessfull!", HttpStatus.BAD_REQUEST);
////
////	}
//
//	@GetMapping("/getUser/{uid}")
//
//	public ResponseEntity<?> getUser(@PathVariable("uid") Integer id, HttpServletRequest request) throws UserException {
//		
//
//		User user = service.getUserById(id);
//		if (user == null) {
//			throw new UserException("Appointment with appointment id:" + id + "not found");
//		}
//		return new ResponseEntity<User>(user, HttpStatus.OK);
//	}
//
//	@GetMapping("signout")
//	public ResponseEntity<String> signOut(HttpServletRequest request) {
//		
//
//		
//		return new ResponseEntity<String>("Logout Successfull!", HttpStatus.OK);
//
//	}
//
//
//	@PatchMapping("/{id}/{userName}/{password}")
//	public ResponseEntity<?> updateCredentials(@PathVariable("id") Integer userId, @PathVariable("userName") String userName,
//			@PathVariable("password") String password,HttpServletRequest request) throws UserException {
//		User user = checkUserLoggedIn(request);
//		if(!user.getRole().equals("admin"))
//			throw new UserException("Invalid Operation");
//		
//		User userToBeUpdated = service.getUserById(userId);
//
//		User updatedUser = service.updateCredentials(userToBeUpdated, userName, password);
//		if (updatedUser != null) {
//			return new ResponseEntity<String>("Credentials updated successfully", HttpStatus.OK);
//		} else
//			return new ResponseEntity<String>("Not able to update credentials", HttpStatus.NOT_FOUND);
//
//	}
//	
//	public User checkUserLoggedIn(HttpServletRequest request) throws UserException {
//		HttpSession session = request.getSession(false);
//		if(session == null) {
//			throw new UserException("You should login first");
//		}
//		String userName = (String) session.getAttribute("name");
//		User user = repository.findByUserName(userName);
//		return user;
//				
//	}

}
