package com.mobile.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mobile.app.entity.Orders;
import com.mobile.app.entity.Payment;
import com.mobile.app.entity.Payment.PaymentMode;
import com.mobile.app.entity.Payment.PaymentStatus;
import com.mobile.app.exception.PaymentException;
import com.mobile.app.repository.PaymentRepository;
import com.mobile.app.service.PaymentService;

@RunWith(SpringRunner.class)
@SpringBootTest

public class PaymentServiceImplTest {

	@Autowired
	private PaymentService paymentService;

	@MockBean
	private PaymentRepository paymentRepository;

	Payment payment = new Payment(1, PaymentMode.UPI, PaymentStatus.PAID);
	Orders order = new Orders();

	@Test
	@DisplayName("Test for adding Payment")
	public void addPaymentTest() {
		Mockito.when(paymentRepository.save(payment)).thenReturn(payment);
		assertEquals(payment, paymentService.addPayment(payment));
	}

	public void testGetPaymentByIdThrowsPaymentException() {
		Integer paymentId = 123456;
		try {
			paymentService.getPaymentById(paymentId);
		} catch (PaymentException e) {
			assertEquals("Paymantwith id" + paymentId + "is not found", e.getMessage());
		}
	}

	@Test
	@DisplayName("Test for Deleteing Payment by Id")
	public void deletePaymentTest() throws PaymentException {
		Integer orderId = 1;
		// Mockito.when(paymentRepository.deleteById(orderId).thenReturn(payment);
		paymentService.deletePaymentById(orderId);
		Mockito.when(paymentRepository.findById(1)).thenReturn(java.util.Optional.of(payment));

		Mockito.verify(paymentRepository, times(1)).deleteById(1);
	}

	@Test
	public void testDeletePaymentThrowsPaymentException() {
		Integer paymentId = 123456;
		try {
			paymentService.deletePaymentById(paymentId);
		} catch (PaymentException e) {
			assertEquals("payment with id" + paymentId + "is not found", e.getMessage());
		}
	}

	@Test
	@DisplayName("Test for payment details by id")
	public void getPaymentDetailsTest() {

		Mockito.when(paymentRepository.findById(100)).thenReturn(java.util.Optional.ofNullable(payment));

	}

	@Test
	@DisplayName("Test for getting all payments")
	public void getAllPaymentDetails() throws PaymentException {
		Mockito.when(paymentRepository.findAll())
				.thenReturn(Stream.of(new Payment(), new Payment()).collect(Collectors.toList()));

		assertEquals(2, paymentService.getAllPayments().size());
		verify(paymentRepository, times(1)).findAll();

	}

	@Test
	public void testGetAllPaymentDetailsExceptionThrowsException() throws PaymentException {
		paymentService.getAllPayments();
	}

	@Test
	@DisplayName("Test for Updating Payment")
	public void updatePaymentTest() {
		java.util.Optional<Payment> payment = paymentRepository.findById(1);
		if (payment.isPresent()) {
			payment.get().setPaymentMode(PaymentMode.UPI);
			Mockito.doReturn(paymentRepository.save(payment.get()));

		}
		java.util.Optional<Payment> updatedpayment = paymentRepository.findById(1);
		if (updatedpayment.isPresent()) {
			assertThat(updatedpayment.get().getPaymentMode().equals("UPI"));
		}
	}

	@Test
	public void testUpdatePaymentThrowsPaymentException() throws PaymentException {
		Integer paymentId = 123456;
		Payment payment = new Payment();
		paymentService.updatePayment(payment, paymentId);
	}
}
