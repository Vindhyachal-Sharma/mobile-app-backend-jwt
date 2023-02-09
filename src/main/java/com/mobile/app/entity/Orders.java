package com.mobile.app.entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Orders {

	@Id
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
	private Map<Integer, Mobile> mobilesMap = new HashMap<>();

	public Orders() {
		super();
	}

	public Orders(Integer id, LocalDate orderDate, LocalDate dispatchDate, Double cost, Double totalCost,
			Integer quantity, String status, Payment payment, Map<Integer, Mobile> mobilesMap) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.dispatchDate = dispatchDate;
		this.cost = cost;
		this.totalCost = totalCost;
		this.quantity = quantity;
		this.status = status;
		this.payment = payment;
		this.mobilesMap = mobilesMap;
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

	public Map<Integer, Mobile> getMobilesMap() {
		return mobilesMap;
	}

	public void setMobilesMap(Map<Integer, Mobile> mobilesMap) {
		this.mobilesMap = mobilesMap;
	}

}
