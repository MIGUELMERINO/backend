package com.tecgurus.puntoventa.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO implements Serializable{

	private static final long serialVersionUID = -4897291990950563531L;
	
	@Schema(description = "estatus", example = "500, 200, 201")
	private String clave;
	@Schema(description = "puede ser cualquier enunciado.", 
			example = "ejemplo: se elimina correctame un elemento!")
	private String valor;
	@Schema(description = "Puede ser cualquier DTO que solicite el frontend")
	// {} el objeto estaria vacio
	private Object playLoad;
	

}


/*
 * {
 * 	clave: null,
 *  valor: "",
 *  playLoad {
 *  	// puede ser cualquier clase que yo quiera.
 *  }
 * }
 */
