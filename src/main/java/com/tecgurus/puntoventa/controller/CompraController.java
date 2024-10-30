package com.tecgurus.puntoventa.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecgurus.puntoventa.config.Constantes;
import com.tecgurus.puntoventa.dto.CompraDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.service.CompraService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Compra", description = "Servicio que maneja la compra de un producto.")
@RestController
@RequestMapping(Constantes.API + "compra")
@CrossOrigin
@AllArgsConstructor
public class CompraController {

	private CompraService compraService;

	/***
	 * Lista de todas las compras.
	 * 
	 * @param pageNo   numero de pagina.
	 * @param pageSize total de elementos a mostrar.
	 * @return lista de compras.
	 */
	@Operation(summary = "Lista de las compras del usuario y cliente.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consulta realizada correctamente!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@GetMapping
	public ResponseDTO listaCompras(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		return compraService.listarCompras(pageNo, pageSize);
	}

	/***
	 * Metodo que agrega una compra.
	 * 
	 * @param compra datos para agregar compra
	 * @return una compra registrada.
	 */
	@Operation(summary = "Regista una nueva compra.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Se registro correctamente la compra!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.OK, description = Constantes.OK_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@PostMapping
	public ResponseDTO agregarCompra(@Parameter(name = "compra") @Valid @RequestBody final CompraDTO compra) {
		return compraService.agregaCompra(compra);
	}

	/***
	 * Servicio que buscar una compra mediante el usuario.
	 * 
	 * @param idUsuario identificador del usuario.
	 * @return lista vacia o llena de las compras de cada usuario.
	 */
	@Operation(summary = "Lista de las compras del usuario y cliente.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consulta realizada correctamente!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	// regla si se relacion con otra entidad se debe de colocar el path de dicha
	// entidad.
	// /api/compra/{idUsuario}/usuario (mala practica).
	// /api/compra/usuario/{idUsuario} (buena practica)
	// /api/compra/{idCompra}/usuario/{idUsuario} (buena practica).
	@GetMapping("/usuario/{idUsuario}")
	public ResponseDTO busquedaCompra(
			@Parameter(name = "idUsuario", description = "Indentificador del usuario", example = "1") @PathVariable Integer idUsuario) {
		return compraService.busquedaCompraId(idUsuario);
	}

}
