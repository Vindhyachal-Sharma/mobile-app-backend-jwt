package com.mobile.app.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private LocalDate orderDate;
	private LocalDate dispatchDate;
	private Double cost;
	private Double totalCost;
	private Integer quantity;
	private String status;

	@OneToOne
	private Payment payment;

	@OneToMany
	private List<Mobile> mobiles = new ArrayList<>();

	public Orders() {
		super();
	}

	public Orders(Integer id, LocalDate orderDate, LocalDate dispatchDate, Double cost, Double totalCost,
			Integer quantity, String status, Payment payment, List<Mobile> mobiles) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.dispatchDate = dispatchDate;
		this.cost = cost;
		this.totalCost = totalCost;
		this.quantity = quantity;
		this.status = status;
		this.payment = payment;
		this.mobiles = mobiles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(LocalDate dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	

	public List<Mobile> getMobiles() {
		return mobiles;
	}

	public void setMobiles(List<Mobile> mobiles) {
		this.mobiles = mobiles;
	}

}
