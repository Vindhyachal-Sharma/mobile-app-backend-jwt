package com.mobile.app.exception;

public class JwtTokenMalformedException extends Exception {
	public JwtTokenMalformedException(String msg) {
		super(msg);
	}
}
