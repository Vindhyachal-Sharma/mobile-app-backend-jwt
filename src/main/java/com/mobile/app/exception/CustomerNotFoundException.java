package com.mobile.app.exception;

@SuppressWarnings("serial")
public class CustomerNotFoundException extends Exception {
	public CustomerNotFoundException(String msg) {
		super(msg);
	}
}
