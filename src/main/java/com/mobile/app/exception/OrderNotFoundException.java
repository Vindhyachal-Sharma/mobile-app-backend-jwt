package com.mobile.app.exception;

@SuppressWarnings("serial")
public class OrderNotFoundException  extends Exception{
	
	public OrderNotFoundException(String msg) {
		super(msg);
	}
}
