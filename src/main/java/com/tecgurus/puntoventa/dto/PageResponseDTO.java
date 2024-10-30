package com.tecgurus.puntoventa.dto;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDTO {

	@Schema(description = "Listado de registros.")
	private List<?> body;
	@Schema(description = "Numero de pagina", example = "1")
	private int pageNo;
	@Schema(description = "Numero a elementos", example = "10")
	private int pageSize;
	@Schema(description = "Total de registros", example = "100")
	private long totalElements;
	@Schema(description = "Total de paginas", example = "5")
	private int totalPages;
	@Schema(description = "Siguiente", example = "true, false")
	private boolean last;
}
