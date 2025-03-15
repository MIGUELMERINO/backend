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
import com.tecgurus.puntoventa.config.Enpoint;
import com.tecgurus.puntoventa.dto.CompraProductoDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.service.CompraProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Compra Producto", description = "Servicio que maneja una compra con su producto")
@RestController
@RequestMapping(Constantes.API + Enpoint.COMPRA_PRODUCTO)
@CrossOrigin
@AllArgsConstructor
public class CompraProductoController {

	private CompraProductoService compraProductoS;

	/***
	 * Lista de compra con su producto.
	 * 
	 * @param pageNo   numero de pagina.
	 * @param pageSize total de elementos a mostrar.
	 * @return Lista con las compras y su producto.
	 */
	@Operation(summary = "Lista de la compra con su producto.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "La consulta fue exitosa!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@GetMapping
	public ResponseDTO listarComprasProductos(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		return compraProductoS.listarComprasProductos(pageNo, pageSize);
	}

	/**
	 * Metodo que registra un nuevo producto con su compra.
	 * 
	 * @param compraProducto datos de la compra.
	 * @return una compra con sus producto.
	 */
	@Operation(summary = "Servicio que crea una nueva compra con sus producto.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Registro completo.!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.OK, description = Constantes.OK_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@PostMapping
	public ResponseDTO agregarCompraProducto(
			@Parameter(name = "compraProducto") @Valid @RequestBody final CompraProductoDTO compraProducto) {
		return compraProductoS.agregaCompraProducto(compraProducto);
	}

	/***
	 * Metodo que realiza una busqueda mediante el identificador.
	 * 
	 * @param idCompraProducto identificador de la compra producto.
	 * @return una compra y su producto.
	 */
	@Operation(summary = "Servicio que busca una compra y su producto.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "La actualizacion fue exitosa!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@GetMapping("/{idCompraProducto}")
	public ResponseDTO compraProductoId(
			@Parameter(name = "idCompraProducto", description = "identificador del compra y su producto", example = "1") @PathVariable Integer idCompraProducto) {
		return compraProductoS.listarCompraProducto(idCompraProducto);
	}

	/***
	 * Metodo que busca mediante el idcompra.
	 * 
	 * @param idCompra identificador de la compra
	 * @return detalle de la compra.
	 */
	@Operation(summary = "Detalle de la compra")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "La consulta fue exitosa!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@GetMapping(Enpoint.COMPRA + "/{idCompra}")
	public ResponseDTO busquedaCompra(
			@Parameter(name = "idCompra", description = "identificador de la compra", example = "1") @PathVariable Integer idCompra) {
		return compraProductoS.busquedaCompra(idCompra);
	}

}
