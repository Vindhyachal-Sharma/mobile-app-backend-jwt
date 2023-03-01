package com.mobile.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Payment;
import com.mobile.app.service.PaymentService;

@RestController
@CrossOrigin("*")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

//	@PostMapping("/payment")
//	public Payment registerPayment(@Valid @RequestBody Payment payment) {
//
//		return paymentService.addPayment(payment);
//	}

//	@PostMapping("/payment/{cartId}")
//	public Payment addPaymentToCart(@Valid @RequestBody Payment payment, @PathVariable("cartId") Integer cartId)
//			throws PaymentNotFoundException {
//
//		return paymentService.addPaymentToCart(payment, cartId);
//	}

//	@PutMapping("/payment")
//	public Payment updatePayment(@Valid @RequestBody Payment updatePayment, @PathVariable("id") Integer id) {
//
//		return paymentService.updatePayment(updatePayment, id);
//	}

//	@DeleteMapping("/payment/{id}")
//	public Payment deletePaymentById(@Valid @PathVariable("id") Integer id) throws PaymentNotFoundException {
//
//		return this.paymentService.deletePaymentById(id);
//
//	}

	@GetMapping("/allPayments/")
	public List<Payment> getAllPayments() {
		return paymentService.getAllPayments();
	}

}
