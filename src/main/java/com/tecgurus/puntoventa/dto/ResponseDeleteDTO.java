package com.tecgurus.puntoventa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class ResponseDeleteDTO {

	@Schema(description = "estatus", example = "200")
	private String clave;
	@Schema(description = "mensaje")
	private String valor;

}
