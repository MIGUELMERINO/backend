package com.tecgurus.puntoventa.security.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tecgurus.puntoventa.entity.Usuario;
import com.tecgurus.puntoventa.repository.UsuarioRepository;
import com.tecgurus.puntoventa.security.model.UserDetailsJWT;

@Service
public class UserDatailServices implements UserDetailsService {

	@Autowired
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
		return new UserDetailsJWT(usuario.getIdUsuario(), email, usuario.getPassword(), roles);
	}

}
