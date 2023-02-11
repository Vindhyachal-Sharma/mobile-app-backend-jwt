package com.mobile.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mobile.app.exception.OrderException;
@RestControllerAdvice
public class OrderControllerAdvice {
	@ExceptionHandler(OrderException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleMobileExceptions(OrderException e) {

		return e.getMessage();
}
}
