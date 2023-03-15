package com.mobile.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Payment;
import com.mobile.app.exception.PaymentNotFoundException;
import com.mobile.app.service.PaymentService;

@RestController
@CrossOrigin("*")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/payment")
	public Payment registerPayment(@Valid @RequestBody Payment payment) {

		return paymentService.addPayment(payment);
	}

	@PutMapping("/payment")
	public Payment updatePayment(@Valid @RequestBody Payment updatePayment, @PathVariable("id") Integer id) {

		return paymentService.updatePayment(updatePayment, id);
	}
	@DeleteMapping("/payment/{id}")
	public Payment deletePaymentById(@Valid @PathVariable("id") Integer id) throws PaymentNotFoundException {

		return this.paymentService.deletePaymentById(id);

	}

	@GetMapping("/allPayments")
	public List<Payment> getAllPayments() {
		return paymentService.getAllPayments();
	}

}
