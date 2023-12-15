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
public class ProductoDTO implements Serializable{

	private static final long serialVersionUID = -5034634233639057136L;
	
	@Schema(description = "indenticador del producto", example = "1")
	private Integer clave;
	@Schema(description = "Nombre del producto", example = "Jabon en polvo")
	private String nombre;
	@Schema(description = "Descripcion del producto", example = "producto en polvo de 850g.")
	private String descripcion;
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	@Schema(description = "precio del producto", example = "80.50")
	private Double precio;
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	@Schema(description = "cantidad", example = "10")
	private Integer stock;
	@Schema(description = "", example = "")
	private String sku;
	@Schema(description = "imagen del producto", example = "example.jpg")
	private String imagen = "";
	@Schema(description = "datos de la categoria por producto")
	private CategoriaDTO categoria;
	

}
