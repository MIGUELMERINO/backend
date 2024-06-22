package com.tecgurus.puntoventa.dto;

import java.io.Serializable;
import java.sql.Date;

import com.tecgurus.puntoventa.config.Constantes;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompraDTO implements Serializable {

	private static final long serialVersionUID = 4501916349860510806L;

	@Schema(description = "identificador de la compra", example = "1")
	private Integer clave;
	@Schema(description = "total de la compra", example = "100.90")
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	private Double total;
	@Schema(description = "fecha de la compra", example = "2023-11-25 00:00:00TZ")
	@NotNull(message = Constantes.NOTNULL)
	@NotBlank(message = Constantes.NOTBLANK)
	private Date fecha;
	@Schema(description = "datos del usuario")
	private UsuarioDTO usuario;
	@Schema(description = "datos del cliente")
	private ClienteDTO cliente;

}
