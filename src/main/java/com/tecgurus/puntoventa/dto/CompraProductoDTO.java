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
public class CompraProductoDTO implements Serializable {

	private static final long serialVersionUID = 7514983181684196198L;

	@Schema(description = "Identificador de compra producto", example = "1")
	private Integer clave;
	@Schema(description = "datos de la compra")
	private CompraDTO compra;
	@Schema(description = "datos del producto")
	private ProductoDTO producto;
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	@Schema(description = "cantidad de producto", example = "10")
	private Integer cantidad;
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	@Schema(description = "costo del producto", example = "10.50")
	private Double costo;

}
