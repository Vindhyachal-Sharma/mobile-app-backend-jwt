package com.mobile.app.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.mobile.app.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	private String secret = "javatechie";

	// get username from jwt token
	public String getUsername(String token) {
		return getClaim(token, Claims::getSubject);
	}

	// get expiration date from jwt token
	public Date getExpiration(String token) {
		return getClaim(token, Claims::getExpiration);
	}

	public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);
	}

	// for retrieve any information from token we will need the secret key
	private Claims getAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		return getExpiration(token).before(new Date());
	}

	// generate token for user
	public String generateToken(User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", user.getRole());
		return createToken(claims, user);
	}

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	private String createToken(Map<String, Object> claims, User user) {

		return Jwts.builder().setClaims(claims).setSubject(user.getUserName()).setIssuer(user.getRole())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// validate token
	public Boolean validateToken(String token/* , UserDetails userDetails */) {
//		final String username = getUsername(token);
		return (/* username.equals(userDetails.getUsername()) && */!isTokenExpired(token));
	}

	public String getRoleFromToken(String token) {
		return getClaim(token, Claims::getIssuer);
	}

}