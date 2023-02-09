package com.mobile.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Orders;
import com.mobile.app.entity.Payment;
import com.mobile.app.exception.PaymentException;
import com.mobile.app.repository.OrderRepository;
import com.mobile.app.repository.PaymentRepository;
@Service
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Payment addPayment(Payment payment) {
		
		return paymentRepository.save(payment);
	}

	@Override
	public Payment updatePayment(Payment payment,Integer id) {
		
		if(paymentRepository.existsById(id)) {
			Payment paymentToBeUpdated = paymentRepository.findById(id).get();
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
	public Payment getPaymentById(Integer id) {
		Optional<Payment> payment =paymentRepository.findById(id);
		if(payment.isPresent()) {
			return payment.get();
		}else {
			return null;
		}
	}

	@Override
	public Payment addPaymentToOrder(Payment payment, Integer id) throws PaymentException {
		Optional<Orders> existingOrder = orderRepository.findById(id);
		if(existingOrder.isEmpty()) {
			throw new PaymentException("Order not Found");
		}
		Orders foundOrders=existingOrder.get();
		Payment newPayment=addPayment(payment);
		foundOrders.setPayment(newPayment);
		orderRepository.save(foundOrders);
		return payment;
		
	}

	
}
