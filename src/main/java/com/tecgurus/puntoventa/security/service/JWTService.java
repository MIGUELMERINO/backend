package com.tecgurus.puntoventa.security.service;

import java.time.Duration;
import java.time.Instant;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

@Service

public class JWTService {
	
	private static final Duration JWT_TOKEN_IAT = Duration.ofMinutes(60);
	
	private final Algorithm hmac512;
	private final JWTVerifier verifier;

    /**
     * Constructor de la clase.
     * @param secret obtendremos una cadena para validar.
     * **/	
	public JWTService(@Value("${spring.application.secret.key}") String secret) {
		this.hmac512 = Algorithm.HMAC512(secret);
		this.verifier = JWT.require(hmac512).build();
	}
	
	/***
	 * Metodo de generacion de un token.
	 * @param userDetails datos obtenidos.
	 * @return una cadena que ocuparemos para validar si tiene permisos o no.
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
	 * Metodo valida token.
	 * @param token cadena creada por el metodo anterior cifrada.
	 * @return retorna una cadena valida.
	 */
	public String validaToken(String token) {
		return verifier.verify(token).getSubject();
	}
	

}
