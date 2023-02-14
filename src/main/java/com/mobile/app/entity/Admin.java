package com.mobile.app.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Admins")
public class Admin /* extends User */ {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Pattern(regexp = "[a-zA-Z0-9]{3,}", message = "name must be min 3 chars, special chars not allowed.")
	private String name;
	@Email(message = "Please enter a valid email Id", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
	@NotNull(message = "Please enter a valid email Id")
	private String email;
	@Pattern(regexp = "[0-9]{10}", message = "Phone number must be 10 gits")
	private long mobileNo;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(Integer id,
			@Pattern(regexp = "[a-zA-Z0-9]{3,}", message = "name must be min 3 chars, special chars not allowed.") String name,
			@Email(message = "Please enter a valid email Id", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}") @NotNull(message = "Please enter a valid email Id") String email,
			@Pattern(regexp = "[0-9]{10}", message = "Phone number must be 10 gits") long mobileNo) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

}
