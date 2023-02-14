package com.mobile.app.exception;

@SuppressWarnings("serial")
public class CartNotFoundException extends Exception{
	public CartNotFoundException(String msg) {
		super(msg);
	}
}
