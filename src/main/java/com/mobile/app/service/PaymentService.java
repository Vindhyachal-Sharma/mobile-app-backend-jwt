package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Payment;
import com.mobile.app.exception.PaymentNotFoundException;

public interface PaymentService {
	Payment getPaymentById(Integer id) throws PaymentNotFoundException;

	Payment addPayment(Payment payment);

	Payment updatePayment(Payment payment, Integer id);

	Payment deletePaymentById(Integer id) throws PaymentNotFoundException;

//	Payment addPaymentToCart(Payment payment, Integer cartId) throws PaymentNotFoundException;

	List<Payment> getAllPayments();

}
