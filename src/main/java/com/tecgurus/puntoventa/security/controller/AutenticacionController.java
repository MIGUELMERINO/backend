package com.tecgurus.puntoventa.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecgurus.puntoventa.security.dto.RequestDTO;
import com.tecgurus.puntoventa.security.dto.ResponseJWTDTO;
import com.tecgurus.puntoventa.security.service.JWTService;
import com.tecgurus.puntoventa.security.service.UserDatailServices;
import com.tecgurus.puntoventa.config.Constantes;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Autentificador", 
	 description = "autentificacion de usuarios registrados.")
@RestController
@RequestMapping(Constantes.API + "authentication")
@CrossOrigin
@Slf4j 
public class AutenticacionController {
	
	@Autowired
	private UserDatailServices userDetailS;
	
	@Autowired
	private JWTService serviceJTW;
	
	@Autowired
	private AuthenticationManager authenticationManager;

    @Operation(summary = "Servicio autentificador para el usuario.")
	@ApiResponses({
		@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consultas realizada correctamente!",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = ResponseJWTDTO.class))}),
		@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content),
		@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description  = Constantes.UNAUTHORIZED_V, content = @Content),
		@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content),
		@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content),
		@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	})
	@PostMapping
	public ResponseJWTDTO authentication(@RequestBody @Valid final RequestDTO request) {
        log.info(request.toString());
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getCorreo(), request.getPassword()));
		final UserDetails userDetails = userDetailS.loadUserByUsername(request.getCorreo());
		final String token = serviceJTW.gereraToken(userDetails);
		ResponseJWTDTO dto = new ResponseJWTDTO();
		dto.setToken(token);
		return dto;
	}
	
	
	

}
