package com.tecgurus.puntoventa.dto;

import java.io.Serializable;

import com.tecgurus.puntoventa.config.Constantes;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = -7071768162701192925L;

	@Schema(description = "indentificador del usuario", example = "1")
	private Integer clave;
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	@Schema(description = "correo electronico", example = "example@example.com")
	private String correo;
	@Schema(description = "password", example = "TecGur1$")
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	@Pattern(regexp = Constantes.VALID_PASSWORD, message = "No cumple con los requerimiento minimos de un password seguro")
	private String password = "";
	@Schema(description = "Nombre del usuario", example = "Miguel")
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	private String nombre;
	@Schema(description = "Apellido Paterno", example = "Perez")
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	private String apellidoP;
	@Schema(description = "Apellido Materno", example = "Perez")
	private String apellidoM = "";
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	@Schema(description = "estatus del usuario", example = "activo, inactivo")
	private String estatus; // activo o inactivo
	@Schema(description = "rol de usuario", example = "ADMIN, USER")
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	private String rol; // entidad rol

}
