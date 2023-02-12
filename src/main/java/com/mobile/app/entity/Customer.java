package com.mobile.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Customer /* extends User */{
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;
//	@NotBlank(message = "Email is mandatory")
//	 @Email(message = "Please enter a valid email Id", regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
//    @NotNull(message = "Please enter a valid email Id")	
	private String email;
//	@Pattern(regexp = "[0-9]{10}",message = "Phone number must be 10 digits")
	private long mobileNo;

	@OneToOne
	private Cart cart;

	@OneToMany 
	private List<Orders> orders = new ArrayList<>();
	public Customer() {
		super();
	}
//	public Customer(Integer id, String username, String password, String role, String name, String email, long mobileNo,
//			Cart cart, List<Orders> orders) {
//		super(id, username, password, role);
//		this.name = name;
//		this.email = email;
//		this.mobileNo = mobileNo;
//		this.cart = cart;
//		this.orders = orders;
//	}
	

//	public Customer(Integer id, String username, String password, String role) {
//		super(id, username, password, role);
//
//	}

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