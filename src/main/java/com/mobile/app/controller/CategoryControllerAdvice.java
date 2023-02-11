package com.mobile.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mobile.app.exception.CategoryException;
@RestControllerAdvice
public class CategoryControllerAdvice {
	@ExceptionHandler(CategoryException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleMobileExceptions(CategoryException e) {

		return e.getMessage();
}
}