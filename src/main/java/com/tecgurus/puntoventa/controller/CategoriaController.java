package com.tecgurus.puntoventa.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecgurus.puntoventa.config.Constantes;
import com.tecgurus.puntoventa.config.Enpoint;
import com.tecgurus.puntoventa.dto.CategoriaDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.ResponseDeleteDTO;
import com.tecgurus.puntoventa.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Categoria", description = "Servicio que maneja el catalogo de categorias")
@RestController
@RequestMapping(Constantes.API + Enpoint.CATEGORIA)
@CrossOrigin
@AllArgsConstructor
public class CategoriaController {

	private CategoriaService categoriaS;

	/***
	 * Metodo que me retorna una lista de categorias. Metodo HTPP tipo get
	 * 
	 * @param pageNo   numero de pagina.
	 * @param pageSize numero total del elementos a mostrar.
	 * @return lista.
	 */
	@Operation(summary = "Servicio que muestras una lista de categorias.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consulta realizado correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@GetMapping // lectura de datos (read)
	public ResponseDTO listarCategoria(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		return categoriaS.listaCategorias(pageNo, pageSize);
	}

	/**
	 * Metodo que retorna datos mediante un identificador
	 * 
	 * @param idCategoria idenfiticador de la categoria.
	 * @return Lista de un valor por su identificador o reterno vacio.
	 **/
	@Operation(summary = "Servicio que muestras una categoria.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consulta realizado correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@GetMapping("/{idCategoria}")
	public ResponseDTO listaCategoriaID(
        @Parameter(name = "idCategoria", description = "identificador de la categoria", required = true)
        @PathVariable final Integer idCategoria) {
		return categoriaS.listaCategoriaId(idCategoria);
	}

	/***
	 * Metodo que agrega una nueva categoria.
	 * 
	 * @param categoria datos de la categoria.
	 * @return una categoria registrada.
	 */
	@Operation(summary = "Servicio crea una nueva categoria.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Categoria registrada correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.OK, description = Constantes.OK_V, content = @Content)
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@PostMapping // escritura de datos (create).
	public ResponseDTO nuevaCategoria(
			@Parameter(name = "categoria", description = "datos necesarios para crear una categoria.") @Valid @RequestBody final CategoriaDTO categoria) {
		return categoriaS.agregaCategoria(categoria);
	}

	/***
	 * Metodo de actualizacion.
	 * 
	 * @param categoria   datos a actualizar.
	 * @param idCategoria identificador de categoria
	 * @return retorna una categoria actualizada.
	 */
	@Operation(summary = "Servicio que actualiza una categoria.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Se actualizo la categoria correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.OK, description = "Categoria actualizada exitosamente!", content = @Content)
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@PutMapping("/{idCategoria}")
	public ResponseDTO actualizaCategoria(
			@Parameter(name = "categoria", description = "datos para actualizar una categoria") @Valid @RequestBody final CategoriaDTO categoria,
			@Parameter(name = "idCategoria", description = "identificador de la categoria", example = "1") @PathVariable final Integer idCategoria) {
		return categoriaS.actualizarCategoria(categoria, idCategoria);
	}

	/***
	 * Metodo eliminar
	 * 
	 * @param id identificador de categoria.
	 * @return respuesta.
	 */
	@Operation(summary = "Servicio que elimina complemetamente una categoria.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Se elimina correctamente la categoria.", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDeleteDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@DeleteMapping("/{idCategoria}")
	public ResponseDeleteDTO eliminaCategoria(
			@Parameter(name = "idCategoria", description = "identificador de la categoria", example = "1") @PathVariable("idCategoria") final Integer id) {
		return categoriaS.eliminarCategoria(id);
	}

	/***
	 * Metodo de busqueda por nombre de la categoria.
	 * 
	 * @param nombre el nombre de la categoria.
	 * @return Lista de categorias encontradas por el nombre.
	 */
	@Operation(summary = "Servico que realiza una busqueda de una categoria por nombre.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consulta de la busqueda realizada correctamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@GetMapping(Enpoint.BUSQUEDA)
	public ResponseDTO busqueda(
			@Parameter(name = "nombre", description = "Nombre de la categoria", example = "Refresco") @RequestParam final String nombre) {
		return categoriaS.busquedaCategoria(nombre);
	}

}
