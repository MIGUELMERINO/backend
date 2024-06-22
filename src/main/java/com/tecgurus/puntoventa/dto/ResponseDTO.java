package com.tecgurus.puntoventa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {

	@Schema(description = "estatus", example = "500, 200, 201")
	private String clave;
	@Schema(description = "puede ser cualquier enunciado.", example = "ejemplo: se elimina correctame un elemento!")
	private String valor;
	@Schema(description = "Puede ser cualquier DTO que solicite el frontend")
	private Object playLoad;

}
