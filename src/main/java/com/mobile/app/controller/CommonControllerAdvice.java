package com.mobile.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mobile.app.exception.AdminNotFoundException;
import com.mobile.app.exception.CartNotFoundException;
import com.mobile.app.exception.CategoryNotFoundException;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.JwtTokenMalformedException;
import com.mobile.app.exception.JwtTokenMissingException;
import com.mobile.app.exception.MobileNotFoundException;
import com.mobile.app.exception.OrderNotFoundException;
import com.mobile.app.exception.PaymentNotFoundException;
import com.mobile.app.exception.UserNotFoundException;

@RestControllerAdvice
@CrossOrigin("*")
public class CommonControllerAdvice {
	@ExceptionHandler(AdminNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleMobileExceptions(AdminNotFoundException e) {

		return e.getMessage();
	}

	@ExceptionHandler(CartNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleMobileExceptions(CartNotFoundException e) {

		return e.getMessage();
	}

	@ExceptionHandler(CategoryNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleMobileExceptions(CategoryNotFoundException e) {

		return e.getMessage();
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleMobileExceptions(CustomerNotFoundException e) {

		return e.getMessage();
	}

	@ExceptionHandler(JwtTokenMalformedException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleMobileExceptions(JwtTokenMalformedException e) {

		return e.getMessage();
	}

	@ExceptionHandler(JwtTokenMissingException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleMobileExceptions(JwtTokenMissingException e) {

		return e.getMessage();
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleMobileExceptions(UserNotFoundException e) {

		return e.getMessage();
	}

	@ExceptionHandler(MobileNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleMobileExceptions(MobileNotFoundException e) {

		return e.getMessage();
	}

	@ExceptionHandler(OrderNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleMobileExceptions(OrderNotFoundException e) {

		return e.getMessage();
	}

	@ExceptionHandler(PaymentNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleMobileExceptions(PaymentNotFoundException e) {

		return e.getMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
