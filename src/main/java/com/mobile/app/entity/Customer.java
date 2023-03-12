package com.mobile.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Customer extends User {

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

	private String status;

	@Size(max = 255, message = "Address should not exceed 255 characters")
	private String address;

	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;	

	@OneToMany(cascade = CascadeType.ALL)
	private List<Orders> orders = new ArrayList<>();

	 public enum CustomerAccountStatus{
		Active,InActive
	}
	
	public Customer() {
		super();
	}

	public Customer(
			@NotBlank(message = "Name is mandatory") @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First letter should be capital,Name can only contain letters and spaces") String name,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}") String email,
			@NotBlank(message = "Mobile number is required") @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number") String mobileNo,
			String status,
			@Size(max = 255, message = "Address should not exceed 255 characters") String address, Cart cart,
			List<Orders> orders) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.status = status;
		this.address = address;
		this.cart = cart;
		this.orders = orders;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	
	
	
	
}
