package com.mobile.app.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.User;
import com.mobile.app.exception.UserNotFoundException;
import com.mobile.app.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;

//	@Override
//	public User signIn(User user) throws UserException {
//		// TODO Auto-generated method stub
//		User loggedInUser = repository.getUserByUsernameAndPassword(user.getUserName(), user.getPassword());
//		if (loggedInUser != null)
//			return loggedInUser;
//		throw new UserNotFoundException("User not found in database");
//
//	}

	@Override
	public User changePassword(Integer id, String changedPassword) {

		if (repository.existsById(id)) {

			User userFromDBTable = repository.findById(id).get();
			userFromDBTable.setPassword(changedPassword);
			return userFromDBTable;

		} else {
			return null;
		}

	}

	@Override
	public User updateCredentials(User user, String userName, String password) {
		user.setUserName(userName);
		user.setPassword(password);
		repository.save(user);
		return user;
	}

	@Override
	public User getUserById(Integer id) {
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

	@Override
	public User signOut(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
