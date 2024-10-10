package com.gocabs.cabbooking.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private long jwtExpirationDate;

	// generating JWT
	public String generateToken(Authentication authentication, Long userId) {

		String username = authentication.getName();

		Date currentDate = new Date();

		Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

		Map<String,Object> claims = new HashMap<>();
		claims.put("userId", userId);
		
		String token = Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(expireDate)
				.claim(username, claims)
				.signWith(key())
				.compact();

		return token;

	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	// get username from jwt token
	public String getUsername(String token) {
		return Jwts.parser()
				.verifyWith((SecretKey) key())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	// Extract userId from the JWT token
    @SuppressWarnings("deprecation")
	public Long getUserId(String token) {
        Claims claims = Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getBody();
        return claims.get("userId", Long.class);
    }
	
	//validate token
	public boolean validateToken(String token) {
		Jwts.parser()
			.verifyWith((SecretKey) key())
			.build()
			.parse(token);
			
		return true;
	}

}











