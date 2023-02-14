package com.mobile.app.service;

import com.mobile.app.entity.User;
import com.mobile.app.exception.UserNotFoundException;

public interface UserService {

	// public User signIn(User user) throws UserException;
	public User signOut(User user);

	public User changePassword(Integer id, String changePassword);

	public User updateCredentials(User user, String userName, String password);

	public User getUserById(Integer id);
}
