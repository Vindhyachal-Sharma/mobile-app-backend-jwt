package com.mobile.app.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer quantity;
	private Double cost;
	private String type;
	private String status;

//	@ManyToMany
//	private Map<Integer, Mobile> mobilesMap = new HashMap<>();
	@ManyToMany
	private List<Mobile> mobiles = new ArrayList<>();

	public Cart() {
		super();
	}

	public Cart(Integer id, Integer quantity, Double cost, String type, String status,
			List<Mobile> mobiles/* Map<Integer,Mobile>mobilesMap */) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.cost = cost;
		this.type = type;
		this.status = status;
		this.mobiles = mobiles;
//		this.mobilesMap=mobilesMap;
	}

//	public Map<Integer, Mobile> getMobilesMap() {
//		return mobilesMap;
//	}
//
//	public void setMobilesMap(Map<Integer, Mobile> mobilesMap) {
//		this.mobilesMap = mobilesMap;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Mobile> getMobiles() {
		return mobiles;
	}

	public void setMobiles(List<Mobile> mobile) {
		this.mobiles = mobile;
	}

}
