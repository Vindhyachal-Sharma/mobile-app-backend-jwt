package com.mobile.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Payment;
import com.mobile.app.exception.PaymentException;
import com.mobile.app.service.PaymentService;

@RestController 
public class PaymentController {
	
//	-------------------------------PaymentService-----------------
//	 Payment getPaymentById(Integer id);
//		Payment addPayment(Payment payment);
//		Payment updatePayment(Payment payment,Integer id);
//		Payment deletePaymentById(Integer id) throws PaymentException;
//		Payment addPaymentToOrder(Payment payment,Integer id) throws PaymentException;
//		List<Payment> getAllPayments();

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/payment")
	public Payment registerPayment(@Valid @RequestBody Payment payment) {

		return paymentService.addPayment(payment);
	}
	@PostMapping("/payment/{cartId}")
	public Payment addPaymentToCart(@Valid @RequestBody Payment payment,@PathVariable("cartId") Integer cartId) throws PaymentException {

		return paymentService.addPaymentToCart(payment,cartId);
	}
	
	@PutMapping("/payment")
	public Payment updatePayment(@Valid @RequestBody Payment updatePayment,@PathVariable("id") Integer id) {
		
		return paymentService.updatePayment(updatePayment,id);
	}
	
	@DeleteMapping("/payment/{id}") 
	public Payment deletePaymentById(@Valid @PathVariable("id") Integer id) throws PaymentException {
		
		return this.paymentService.deletePaymentById(id);
		
	}
	@GetMapping("/allPayments/")
	public List<Payment> getAllPayments()
	{
     return paymentService.getAllPayments();
}
	
}
