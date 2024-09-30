package com.tecgurus.puntoventa.security.service;

import java.time.Duration;
import java.time.Instant;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JWTService {
	
	private static final Duration JWT_TOKEN_IAT = Duration.ofMinutes(60);
	
	private final Algorithm hmac512;
	private final JWTVerifier verifier;
	
	private String secret = "$2a$12$x5YdY3x12RJ41yWot9Lt4.LmDjmrAvOWgE3A7Hs5fvKkph2JAb166";
	
	
	public JWTService() {
		this.hmac512 = Algorithm.HMAC512(secret);
		this.verifier = JWT.require(hmac512).build();
	}
	
	/***
	 * 
	 * @param userDetails
	 * @return
	 */
	public String gereraToken(final UserDetails userDetails) {
		final Instant now = Instant.now(); // fecha y hora tal 3123912
		return JWT.create()
				.withSubject(userDetails.getUsername())
				.withIssuer("puntoventa")
				.withIssuedAt(now)
				.withExpiresAt(now.plusMillis(JWT_TOKEN_IAT.toMillis()))
				.sign(hmac512);
	}
	
	/***
	 * 
	 * @param token
	 * @return
	 */
	public String validaToken(String token) {
		return verifier.verify(token).getSubject();
	}
	

}
