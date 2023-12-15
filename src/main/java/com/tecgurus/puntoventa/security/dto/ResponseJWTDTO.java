package com.tecgurus.puntoventa.security.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseJWTDTO implements Serializable {

	private static final long serialVersionUID = -4049781598435609298L;
	
	private String token;

}
