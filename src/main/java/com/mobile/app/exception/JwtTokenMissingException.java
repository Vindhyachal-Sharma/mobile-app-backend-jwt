package com.mobile.app.exception;

public class JwtTokenMissingException extends Exception {
	public JwtTokenMissingException(String msg) {
		super(msg);
	}
}
