package com.tecgurus.puntoventa.security.service;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tecgurus.puntoventa.entity.Usuario;
import com.tecgurus.puntoventa.repository.UsuarioRepository;
import com.tecgurus.puntoventa.security.model.UserDJWT;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDatailServices implements UserDetailsService {

	private UsuarioRepository usuarioRepository;
	
	/**
	 * Metodo que sobreescribe el loadUserByUsername (carga de usuario)
	 * @param email correo electronico del usuario a logearse
	 * @throws UsernameNotFoundException excepcion si no encuentra un usuario
	 * @return un usuario valido.
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmailPassword(email);
		List<SimpleGrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority(usuario.getPerfil()));
		return new UserDJWT(usuario.getIdUsuario(), email, usuario.getPassword(), roles);
	}

}
