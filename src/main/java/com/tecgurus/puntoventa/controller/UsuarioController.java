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
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.ResponseDeleteDTO;
import com.tecgurus.puntoventa.dto.UsuarioDTO;
import com.tecgurus.puntoventa.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Usuario", description = "Servicio que maneja a los usuarios.")
@RestController
@RequestMapping(Constantes.API + "usuario")
@CrossOrigin
@AllArgsConstructor
public class UsuarioController {

	// injecta una instancia mediante IoC que pertenece a Spring.
	private UsuarioService usuarioS;

	/**
	 * Lista de todos los usuarios registrados.
	 * 
	 * @return lista de usuarios.
	 */
	@Operation(summary = "Servicio que lista a los usuarios.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consulta exitosa!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@GetMapping
	public ResponseDTO listarUsuarios() {
		return usuarioS.obtenerUsuarios();
	}

	/**
	 * Lista de usuarios activos.
	 * 
	 * @return lista de usuarios.
	 */
	@Operation(summary = "Servicio que lista a los usuarios con perfil activo.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consulta exitosa!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@GetMapping("/activos")
	public ResponseDTO listaUsuariosActivos() {
		return usuarioS.obtenerUsuariosActivos();
	}

	/**
	 * Metodo que agrega un usuario.
	 * 
	 * @param usuario datos del usuario.
	 * @return un usuario nuevo con estatus 1.
	 */
	@Operation(summary = "Servicio que registra un usuario.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Registro exitoso!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.OK, description = Constantes.OK_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@PostMapping
	public ResponseDTO agregaUsuario(@Parameter(name = "usuario") @Valid @RequestBody final UsuarioDTO usuario) {
		return usuarioS.agregaUsuario(usuario);
	}

	/***
	 * Metodo que actualiza un usuario.
	 * 
	 * @param usuario   datos del usuario.
	 * @param idUsuario identificador del usuario.
	 * @return retorna un usuario actualizado.
	 */
	@Operation(summary = "Servicio que actuliza un usuario.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "actualizacion exitosa!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.OK, description = Constantes.OK_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@PutMapping("/{idUsuario}")
	public ResponseDTO actulizaUsuario(@Parameter(name = "usuario") @Valid @RequestBody final UsuarioDTO usuario,
			@Parameter(name = "idUsuario", description = "identificador del usuario", example = "1") @PathVariable final Integer idUsuario) {
		return usuarioS.actualizaUsuario(usuario, idUsuario);
	}

	/***
	 * Metodo que elimina un usuario logicamente por el estatus.
	 * 
	 * @param idUsuario identificador del usuario.
	 * @return respuesta ok.
	 */
	@Operation(summary = "Servicio que elimina a un usuario.")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Eliminacion exitosa!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	@DeleteMapping("/{idUsuario}")
	public ResponseDeleteDTO eliminaUsuario(
			@Parameter(name = "idUsuario", description = "identificador del usuario", example = "1") @PathVariable final Integer idUsuario) {
		return usuarioS.eliminaUsuario(idUsuario);
	}

	/***
	 * Metodo que obtiene la informacion del usuario.
	 * 
	 * @param correo del usuario.
	 * @return detalle del usuario.
	 */
	@Operation(summary = "Servicio realiza el detalle del usuario por correo")
	@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consulta exitosa!", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)) })
	@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description = Constantes.UNAUTHORIZED_V, content = @Content)
	@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content)
	@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content)
	@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	// info?correo=example@example.com.
	@GetMapping("/info")
	public ResponseDTO infoUser(
			@Parameter(name = "correo", description = "correo del usuario obtenido del token", example = "example@example.com") @RequestParam("correo") final String correo) {
		return usuarioS.infoUsuario(correo);
	}

}
