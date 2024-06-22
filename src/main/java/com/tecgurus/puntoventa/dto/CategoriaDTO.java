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
public class CategoriaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// no contiene anotacion que sea de una entidad
	// no esta la capa entidad serializable (esto es opcional).
	@Schema(description = "identificador de la categoria", example = "1")
	private Integer clave; // no esta colocando una variable descriptiva como id o idCategoria el usuario de front end no sabra su significado.
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	@Schema(description = "nombre de la categoria", example = "Refresco")
	private String nombre;
	@NotBlank(message = Constantes.DESCRIPTION)
	@Schema(description = "descripcion de la categoria", example = "refresco de x marca de 600 ml desechable.")
	private String descripcion;
	
}
