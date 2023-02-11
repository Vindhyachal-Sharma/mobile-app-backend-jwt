package com.mobile.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mobile.app.exception.MobileException;
@RestControllerAdvice
public class MobileControllerAdvice {
	@ExceptionHandler(MobileException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleMobileExceptions(MobileException e) {

		return e.getMessage();
}
}
