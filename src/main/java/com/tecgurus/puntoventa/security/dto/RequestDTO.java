package com.tecgurus.puntoventa.security.dto;

import java.io.Serializable;

import com.tecgurus.puntoventa.config.Constantes;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestDTO implements Serializable {
	
	private static final long serialVersionUID = -7406929881521596287L;
	
	
	@Schema(description = "correo electronico (email)", example = "example@example.com")
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	@Email(message = "el correo debe ser valido")
	private String correo;
	@Schema(description = "password", example = "177Mjdjdn$")
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	@Pattern(regexp = Constantes.VALID_PASSWORD, 
			 message = "No cumple con los requerimiento minimos de un password seguro")
	private String password;

}
