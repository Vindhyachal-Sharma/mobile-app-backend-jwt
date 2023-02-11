package com.mobile.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mobile.app.exception.AdminException;

@RestControllerAdvice
public class AdminControllerAdvice {
	@ExceptionHandler(AdminException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleMobileExceptions(AdminException e) {

		return e.getMessage();
	}
}
 