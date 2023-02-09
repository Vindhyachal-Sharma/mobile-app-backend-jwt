package com.mobile.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Payment {

	@Id
	private Integer paymentId;
	private PaymentMode paymentMode;
	private PaymentStatus paymentStatus;
	
	enum PaymentMode{
		CASH_ON_DELIVERY,CARD
	}
	
	enum PaymentStatus{
		PENDING,SUCCESSFUL
	}
	

	public Payment() {
		super();
		
	}

	public Payment(Integer paymentId, PaymentMode paymentMode, PaymentStatus paymentStatus) {
		super();
		this.paymentId = paymentId;
		this.paymentMode = paymentMode;
		this.paymentStatus = paymentStatus;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
	
}
