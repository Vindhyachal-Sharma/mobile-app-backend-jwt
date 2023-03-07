package com.mobile.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginCredentials implements Serializable{

	
	@Column(unique = true, nullable = false)
	@Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters long")
	@Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Username may only contain alphanumeric characters, underscores, and hyphens")
	private String userName;
	
	
	@NotNull
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "Password must contain at least one digit, one lowercase, one uppercase letter, one special symbol (@#$%^&+=) and no whitespace")
	private String password;

	public LoginCredentials() {
		super();

	}

	

	public LoginCredentials(
			@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username can only contain letters and numbers") String userName,
			@Pattern(regexp = "[a-zA-Z0-9]{6,}", message = "pwd must be 8 chars, no special chars are alllowed") String password) {
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
