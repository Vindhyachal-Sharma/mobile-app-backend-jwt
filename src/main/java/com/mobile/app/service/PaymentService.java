package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Payment;
import com.mobile.app.exception.PaymentException;

public interface PaymentService {
	 Payment getPaymentById(Integer id);
	
	Payment addPayment(Payment payment);

	Payment updatePayment(Payment payment,Integer id);

	Payment deletePaymentById(Integer id) throws PaymentException;

	Payment addPaymentToOrder(Payment payment,Integer id) throws PaymentException;
	
	List<Payment> getAllPayments();
	
}
