package com.mobile.app.jwt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.exception.UserNotFoundException;

import io.jsonwebtoken.SignatureException;

@Service
public class ValidateToken {
	@Autowired
	private JwtUtil jwtUtil;

	public void validateToken(HttpServletRequest request, String role) throws UserNotFoundException {
		final String tokenHeader = request.getHeader("Authorization");

		String jwtToken = null;

		if (tokenHeader == null)
			throw new UserNotFoundException("User Not Logged In or token not included");
		// JWT Token is in the form "Bearer token". Remove Bearer word
		if (!tokenHeader.startsWith("Bearer "))
			throw new UserNotFoundException("Invalid Token");
		jwtToken = tokenHeader.substring(7);
		if (!jwtUtil.getRoleFromToken(jwtToken).equals(role)) {
			throw new UserNotFoundException("Not authorized to perfrom this action");
		}
		jwtToken = tokenHeader.substring(7);
		try {
			if (!(jwtUtil.validateToken(jwtToken)))
				throw new UserNotFoundException("Token Expired. Need Relogin");

		} catch (SignatureException ex) {
			throw new UserNotFoundException("Invalid Token");
		}
	}

}
