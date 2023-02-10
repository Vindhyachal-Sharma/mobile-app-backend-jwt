package com.mobile.app.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Admins")
public class Admin {

	@Id
	private Integer id;
	private String name;

	private String email;
	private String password;

	private long mobileNo;

	@OneToOne
	private Cart cart;

	@OneToMany
	private List<Category> categories = new ArrayList<>();

	@OneToMany
	private List<Orders> orders = new ArrayList<>();

	@OneToMany
	private Map<Integer, Mobile> mobilesMap = new HashMap<>();

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(Integer id, String name, String email, String password, long mobileNo, Cart cart,
			List<Category> categories, List<Orders> orders, Map<Integer, Mobile> mobilesMap) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNo = mobileNo;
		this.cart = cart;
		this.categories = categories;
		this.orders = orders;
		this.mobilesMap = mobilesMap;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	
}
