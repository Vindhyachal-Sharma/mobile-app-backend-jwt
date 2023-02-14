package com.mobile.app.entity;

import javax.validation.constraints.Pattern;

public class Login {
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username can only contain letters and numbers")
	private String userName;
	@Pattern(regexp = "[a-zA-Z0-9]{8,}", message = "pwd must be 8 chars, no special chars are alllowed")
	private String password;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Login(
			@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username can only contain letters and numbers") String userName,
			@Pattern(regexp = "[a-zA-Z0-9]{8,}", message = "pwd must be 8 chars, no special chars are alllowed") String password) {
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
