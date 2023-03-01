package com.mobile.app.entity;

import java.io.Serializable;

public class LoginCredentials implements Serializable{

	// @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username can only contain
	// letters and numbers")
	private String userName;
//	@Pattern(regexp = "[a-zA-Z0-9]{6,}", message = "pwd must be 8 chars, no special chars are alllowed")

	private String password;

	public LoginCredentials() {
		super();

	}

	public LoginCredentials(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
