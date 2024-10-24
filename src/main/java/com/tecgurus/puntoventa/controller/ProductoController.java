package com.tecgurus.puntoventa.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecgurus.puntoventa.config.Constantes;
import com.tecgurus.puntoventa.dto.ProductoDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Producto", description = "Servicio que manejena los productos.")
@RestController
@RequestMapping(Constantes.API + "producto")
@CrossOrigin
@AllArgsConstructor
public class ProductoController {

	private ProductoService productoS;

	/***
	 * Lista de productos.
	 * 
	 * @return lista de productos.
	 */
	@Operation(summary = "Servicio que genera una lista de productos.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consulta exitosa!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@GetMapping
	public ResponseDTO listaProductos() {
		return productoS.listarProductos();
	}

	/**
	 * Servicio que muestra un producto mediante su id.
	 * 
	 * @param idProducto identificador del producto.
	 * @return un producto o vacio.
	 **/
	@Operation(summary = "Servicio que muestra un producto.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consulta exitosa!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@GetMapping("/{idProducto}")
	public ResponseDTO listaProducto(@PathVariable final Integer idProducto) {
		return productoS.listaProducto(idProducto);
	}

	/***
	 * Agregar un producto nuevo.
	 * 
	 * @param producto datos del producto
	 * @return producto nuevo registrado exitosamente.
	 */
	@Operation(summary = "Servicio que crear un producto")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Registro de un producto exitosa!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.OK, description = Constantes.OK_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@PostMapping
	public ResponseDTO agregaProducto(@Parameter(name = "producto") @Valid @RequestBody final ProductoDTO producto) {
		return productoS.agregaProducto(producto);
	}

	/***
	 * Actuliza producto.
	 * 
	 * @param producto   datos del producto
	 * @param idProducto identificador de producto.
	 * @return producto actualizado.
	 */
	@Operation(summary = "Lista de los productos.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consulta exitosa!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@PutMapping("/{idProducto}")
	public ResponseDTO actualizarproducto(@Parameter(name = "producto") @Valid @RequestBody final ProductoDTO producto,
			@Parameter(name = "idProducto", description = "identificador de una producto", example = "1") @PathVariable final Integer idProducto) {
		return productoS.actualizaProducto(producto, idProducto);
	}

	/***
	 * Metodo que busca un producto por caracter o su nombre completo.
	 * 
	 * @param nombre o caracter de un producto.
	 * @return lista de productos con el mismo nombre o caracteres.
	 */
	@Operation(summary = "Servicio busqueda de producto por nombre o caracter.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consulta exitosa!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@GetMapping("/busqueda")
	public ResponseDTO busqueda(
			@Parameter(name = "nombre", description = "nombre del producto o caracter", example = "Pro, producto, p") @RequestParam String nombre) {
		return productoS.busquedaProducto(nombre);
	}
}
