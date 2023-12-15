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

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "autenficacion", 
	 description = "autentificacion de usuarios registrados.")
@RestController
@RequestMapping("/api/authentication")
@CrossOrigin
public class AutenticacionController {
	
	@Autowired
	private UserDatailServices userDetailS;
	
	@Autowired
	private JWTService serviceJTW;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping
	public ResponseJWTDTO authentication(@RequestBody @Valid final RequestDTO request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getCorreo(), request.getPassword()));
		final UserDetails userDetails = userDetailS.loadUserByUsername(request.getCorreo());
		final String token = serviceJTW.gereraToken(userDetails);
		ResponseJWTDTO dto = new ResponseJWTDTO();
		dto.setToken(token);
		return dto;
	}
	
	
	

}
