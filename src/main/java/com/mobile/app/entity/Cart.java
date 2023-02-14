package com.mobile.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Min(value = 0, message = "Cost cannot be neagtive")

	private Integer quantity;
	@Min(value = 0, message = "Cost cannot be neagtive")
	private Double cost;


	@ManyToMany
	private List<Mobile> mobiles = new ArrayList<>();

	@OneToOne
	Payment payment;

	public Cart() {
		super();
	}

	public Cart(Integer id, @Min(value = 0, message = "Cost cannot be neagtive") Integer quantity,
			@Min(value = 0, message = "Cost cannot be neagtive") Double cost, List<Mobile> mobiles, Payment payment) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.cost = cost;
		this.mobiles = mobiles;
		this.payment = payment;
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

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	
}
