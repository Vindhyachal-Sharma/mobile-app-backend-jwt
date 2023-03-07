package com.mobile.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	 
	

	@NotNull
	@Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters long")
	@Pattern(regexp = "^[a-zA-Z0-9_-]{3,}$", message = "Username may only contain alphanumeric characters, underscores, and hyphens")
	private String userName;
	
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull
	@NotNull
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "Password must contain at least one digit, one lowercase, one uppercase letter, one special symbol (@#$%^&+=) and no whitespace")
	private String password;
	
	
	private String role;
	
	public enum Role{
		Admin,customer
	}

	public User() {
		super();

	}

	public User(Integer userId,
			@NotNull @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters long") @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Username may only contain alphanumeric characters, underscores, and hyphens") String userName,
			@NotNull @NotNull @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "Password must contain at least one digit, one lowercase, one uppercase letter, one special symbol (@#$%^&+=) and no whitespace") String password,
			String role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	



}