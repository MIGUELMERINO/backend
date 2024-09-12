package com.tecgurus.puntoventa.security.dto;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseJWTDTO implements Serializable {

	private static final long serialVersionUID = -4049781598435609298L;
    @Schema(description = "token", example = "eyJhbGciOiJIUzUxMiIsInR5cCI6I....")	
	private String token;

}
