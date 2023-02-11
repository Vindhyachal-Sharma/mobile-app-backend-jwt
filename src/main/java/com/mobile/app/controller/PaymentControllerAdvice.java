package com.mobile.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mobile.app.exception.PaymentException;
@RestControllerAdvice
public class PaymentControllerAdvice {
	@ExceptionHandler(PaymentException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleMobileExceptions(PaymentException e) {

		return e.getMessage();
}
}
