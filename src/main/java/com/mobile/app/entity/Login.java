package com.mobile.app.entity;

import javax.validation.constraints.Pattern;

public class Login {
	@Pattern(regexp = "[a-zA-Z0-9]{3,}", message = "name must be min 3 chars, special chars not allowed.")
	private String userName;
	@Pattern(regexp = "[a-zA-Z0-9]{8,}",message = "pwd must be 8 chars, no special chars are alllowed")
	private String password;

	public Login() {

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
