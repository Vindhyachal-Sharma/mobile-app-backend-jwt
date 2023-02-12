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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Pattern(regexp = "[a-zA-Z0-9]{3,}", message = "name must be min 3 chars, special chars not allowed.")
	private String name;
	@Email(message = "Please enter a valid email Id", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
	@NotNull(message = "Please enter a valid email Id")
	private String email;
	@Pattern(regexp = "[0-9]{10}",message = "Phone number must be 10 gits")
	private long mobileNo;

	@OneToOne
	private Cart cart;

	@OneToMany
	private List<Category> categories = new ArrayList<>();

	@OneToMany
	private List<Orders> orders = new ArrayList<>();

	@OneToMany
	private Map<Integer, Mobile> mobilesMap = new HashMap<>();

//	public Admin(Integer id, String username, String password, String role) {
//		/* super(id, username, password, role); */
//
//	}

	public Admin(Integer id, String username, String password, String role, String name, String email, long mobileNo,
			Cart cart, List<Category> categories, List<Orders> orders, Map<Integer, Mobile> mobilesMap) {
//		super(id, username, password, role);
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.cart = cart;
		this.categories = categories;
		this.orders = orders;
		this.mobilesMap = mobilesMap;
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

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public Map<Integer, Mobile> getMobilesMap() {
		return mobilesMap;
	}

	public void setMobilesMap(Map<Integer, Mobile> mobilesMap) {
		this.mobilesMap = mobilesMap;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
