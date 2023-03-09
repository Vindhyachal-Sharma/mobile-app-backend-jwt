package com.mobile.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Admins")
public class Admin extends User {

	@NotBlank(message = "Name is mandatory")
	@Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First letter should be capital,Name can only contain letters and spaces")
	private String name;
	
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
	private String email;
	
	@NotBlank(message = "Mobile number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
	private String mobileNo;

	public Admin() {
		super();
	}

	public Admin(
			@NotBlank(message = "Name is mandatory") @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First letter should be capital,Name can only contain letters and spaces") String name,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}") String email,
			@NotBlank(message = "Mobile number is required") @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number") String mobileNo) {
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