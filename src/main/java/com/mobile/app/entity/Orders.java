package com.mobile.app.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer id;
	
	@NotNull
	@PastOrPresent
	private LocalDate orderDate;
	
	
	@NotNull
	private LocalDate dispatchDate;
	@Min(value = 0, message = "Cost cannot be neagtive")
	private Double cost;
	
	
	private String orderAddress;

	
	private OrderStatus orderStatus;
	
	private Integer quantity;

	@ManyToMany
	private List<Mobile> mobiles = new ArrayList<>();
	
	@OneToOne
	Payment payment;

	public enum OrderStatus{
		PLACED,CANCELLED,RETURNED,PARTIALLY_CANCELLED
	}
	
	public Orders() {
		super();
	}

	public Orders(@NotNull @PastOrPresent LocalDate orderDate, @NotNull LocalDate dispatchDate,
			@Min(value = 0, message = "Cost cannot be neagtive") Double cost, String orderAddress,
			OrderStatus orderStatus, Integer quantity, List<Mobile> mobiles, Payment payment) {
		super();
		this.orderDate = orderDate;
		this.dispatchDate = dispatchDate;
		this.cost = cost;
		this.orderAddress = orderAddress;
		this.orderStatus = orderStatus;
		this.quantity = quantity;
		this.mobiles = mobiles;
		this.payment = payment;
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

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
