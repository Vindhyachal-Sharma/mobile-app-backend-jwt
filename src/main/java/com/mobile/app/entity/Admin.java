package com.mobile.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Admins")
public class Admin {

	@Id
	private Integer id;
	private String name;

	private String email;
	private String password;

	private long mobileNo;

	public Admin(Integer id, String name, String email, String password, long mobileNo) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
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
	
//	@OneToOne
//	private Cart cart;
//	
//	@OneToMany
//	private List<Orders> orders = new ArrayList<>();
//	
////	@OneToOne
////	private Customer customer;
//
//	@OneToMany
//	private List<Category> categories = new ArrayList<>();
//	
//	@OneToMany
//	private Map<Integer, Mobile> mobilesMap = new HashMap<>();
//	
//	@OneToMany
//	private Map<Category, Map<Integer, Mobile>> mobilesCategory = new HashMap<>();
//
//	public Admin(String id, String name, String email, String password, long mobileNo, Cart cart, List<Orders> orders,
//			/* Customer customer, */ List<Category> categories, Map<Integer, Mobile> mobilesMap,
//			Map<Category, Map<Integer, Mobile>> mobilesCategory) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.email = email;
//		this.password = password;
//		this.mobileNo = mobileNo;
//		this.cart = cart;
//		this.orders = orders;
////		this.customer = customer;
//		this.categories = categories;
//		this.mobilesMap = mobilesMap;
//		this.mobilesCategory = mobilesCategory;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public long getMobileNo() {
//		return mobileNo;
//	}
//
//	public void setMobileNo(long mobileNo) {
//		this.mobileNo = mobileNo;
//	}
//
//	public Cart getCart() {
//		return cart;
//	}
//
//	public void setCart(Cart cart) {
//		this.cart = cart;
//	}
//
//	public List<Orders> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Orders> orders) {
//		this.orders = orders;
//	}
//
////	public Customer getCustomer() {
////		return customer;
////	}
////
////	public void setCustomer(Customer customer) {
////		this.customer = customer;
////	}
//
//	public List<Category> getCategories() {
//		return categories;
//	}
//
//	public void setCategories(List<Category> categories) {
//		this.categories = categories;
//	}
//
//	public Map<Integer, Mobile> getMobilesMap() {
//		return mobilesMap;
//	}
//
//	public void setMobilesMap(Map<Integer, Mobile> mobilesMap) {
//		this.mobilesMap = mobilesMap;
//	}
//
//	public Map<Category, Map<Integer, Mobile>> getMobilesCategory() {
//		return mobilesCategory;
//	}
//
//	public void setMobilesCategory(Map<Category, Map<Integer, Mobile>> mobilesCategory) {
//		this.mobilesCategory = mobilesCategory;
//	}
//
	
}
