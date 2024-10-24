package com.tecgurus.puntoventa.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@java.lang.SuppressWarnings("squid:S2160")
public class UserDJWT extends User {

	private static final long serialVersionUID = 186866254695628011L;

	public final Integer id;

	/***
	 * Metodo que crea o asigna valores a un clase User para generar la
	 * authorizacion del usuario.
	 * 
	 * @param id        identicador del usuario.
	 * @param email     correo del usuario
	 * @param password  del usuario
	 * @param authority lista de las perfiles autorizados.
	 */
	public UserDJWT(final Integer id, String email, String password, Collection<? extends GrantedAuthority> authority) {
		super(email, password, authority);
		this.id = id;
	}

}
