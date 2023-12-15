package com.tecgurus.puntoventa.dto;

import java.io.Serializable;

import com.tecgurus.puntoventa.config.Constantes;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = -5238057384581932341L;
	
	@Schema(description = "indentificador del cliente", example = "1")
	private Integer clave;
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	@Schema(description = "Nombre del cliente", example = "Miguel")
	private String nombre;
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	@Schema(description = "Apellido Paterno", example = "Lopez")
	private String apellidoP;
	@Schema(description = "Apellido Materno", example = "Lopez")
	private String apellidoM = "";
	@Schema(description = "RFC", example = "999SDJSKJDKS09L")
	private String rfc = "";

}
