package com.mobile.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Cart {

	@Id
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer id;

	@Min(value = 0, message = "Quantity cannot be neagtive")
	private Integer quantity;
	
	@Min(value = 0, message = "Cost cannot be neagtive")
	private Double cost;

	@ManyToMany
	private List<Mobile> mobiles = new ArrayList<>();

	public Cart() {
		super();
	}

	public Cart(Integer id, @Min(value = 0, message = "Quantity cannot be neagtive") Integer quantity,
			@Min(value = 0, message = "Cost cannot be neagtive") Double cost) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.cost = cost;
	}

	public Cart(Integer id, @Min(value = 0, message = "Cost cannot be neagtive") Integer quantity,
			@Min(value = 0, message = "Cost cannot be neagtive") Double cost, List<Mobile> mobiles) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.cost = cost;
		this.mobiles = mobiles;
		
	}

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

	public List<Mobile> getMobiles() {
		return mobiles;
	}

	public void setMobiles(List<Mobile> mobiles) {
		this.mobiles = mobiles;
	}

	
}
