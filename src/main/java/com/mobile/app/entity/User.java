package com.mobile.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class User {

	@Id
	private Integer id;

	private String username;

	private String role;

	private Boolean isType;

	public User(Integer id, String username, String role, Boolean isType) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
		this.isType = isType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getIsType() {
		return isType;
	}

	public void setIsType(Boolean isType) {
		this.isType = isType;
	}
	

}
