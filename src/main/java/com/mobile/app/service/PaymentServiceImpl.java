	package com.mobile.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Payment;
import com.mobile.app.exception.OrderException;
import com.mobile.app.exception.PaymentException;
import com.mobile.app.repository.CartRepository;
import com.mobile.app.repository.PaymentRepository;
@Service
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public Payment addPayment(Payment payment) {
		
		return paymentRepository.save(payment);
	}

	@Override 
	public Payment updatePayment(Payment payment,Integer id) {
		
		if(paymentRepository.existsById(id)) {
			Payment paymentToBeUpdated = paymentRepository.findById(id).get();
			paymentToBeUpdated.setPaymentStatus(payment.getPaymentStatus());
			paymentToBeUpdated.setPaymentMode(payment.getPaymentMode());
			paymentRepository.save(paymentToBeUpdated);
			paymentRepository.save(payment);
			return paymentToBeUpdated;
		}
		return null;

	}

	@Override
	public Payment deletePaymentById(Integer id) throws PaymentException {
		
		Optional<Payment> optPayment = this.paymentRepository.findById(id);
		if (optPayment.isEmpty())
			throw new PaymentException("Payment id does not exists to delete !");
		Payment payment = optPayment.get();
		this.paymentRepository.delete(payment);
		return payment;
	}

	@Override
	public List<Payment> getAllPayments() {
		
		return paymentRepository.findAll();
	}

	@Override
	public Payment getPaymentById(Integer paymentId)throws PaymentException {
		Optional<Payment> payment =paymentRepository.findById(paymentId);
		if(payment.isPresent()) {
			return payment.get();
		}else {
			throw new PaymentException("Payment with Id"+paymentId+"was not found");
		}
	}

	@Override
	public Payment addPaymentToCart(Payment payment, Integer cartId) throws PaymentException {
		Optional<Cart> existingCart = cartRepository.findById(cartId);
		if(existingCart.isEmpty()) {
			throw new PaymentException("Order not Found");
		}
		Cart foundCart=existingCart.get();
		Payment newPayment=addPayment(payment);
		foundCart.setPayment(newPayment);
		cartRepository.save(foundCart);
		return payment;
		
	}
	

	
}
