package com.mobile.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Admins")
public class Admin extends User {

	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "name must be min 3 chars, special chars not allowed.")
	private String name;
	@Email(message = "Please enter a valid email Id", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
	@NotNull(message = "Please enter a valid email Id")
	
	private String email;
	@Pattern(regexp = "[0-9]{10}", message = "Phone number must be 10 digits")
	private String mobileNo;

	public Admin() {
		super();
	}

	public Admin(
			@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "name must be min 3 chars, special chars not allowed.") String name,
			@Email(message = "Please enter a valid email Id", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}") @NotNull(message = "Please enter a valid email Id") String email,
			@Pattern(regexp = "[0-9]{10}", message = "Phone number must be 10 digits") String mobileNo) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
