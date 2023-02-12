package com.mobile.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@NotBlank(message = "Name is mandatory")
	@Pattern(regexp = "[a-zA-Z0-9]{5,}",message = "name must be min 5 chars, special chars not allowed.")
	private String userName;
	@Pattern(regexp = "[a-zA-Z0-9]{8,}", message = "pwd must be 8 chars, no special chars are alllowed")
	private String password;

	private String role; 

	public User() {
		super();
		
	}

	public User(Integer id, String userName, String password, String role) {
		super();
		this.id = id;  
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
