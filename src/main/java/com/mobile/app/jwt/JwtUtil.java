package com.mobile.app.jwt;

import java.util.Date;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.mobile.app.entity.User;
import com.mobile.app.exception.JwtTokenMalformedException;
import com.mobile.app.exception.JwtTokenMissingException;
import com.mobile.app.exception.UserNotFoundException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtUtil {

	private static String secret = "javatechie";
	
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

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
		Claims claims = Jwts.claims().setSubject(user.getUserName());
		
		return Jwts.builder().setClaims(claims)
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

	
	public  static void validateToken(HttpServletRequest request) throws UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException{
		final String tokenHeader = request.getHeader("Authorization");

		String jwtToken = null;

		if (tokenHeader == null)
			throw new UserNotFoundException("User Not Logged In or token not included");
		// JWT Token is in the form "Bearer token". Remove Bearer word
		if (!tokenHeader.startsWith("Bearer "))
			throw new UserNotFoundException("Invalid Token");
		
		jwtToken = tokenHeader.substring(7);
		
		
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
		} catch (SignatureException ex) {
			throw new JwtTokenMalformedException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalformedException("Invalid JWT token");
		}  catch (UnsupportedJwtException ex) {
			throw new JwtTokenMalformedException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("JWT claims string is empty.");
		}
	}
}