package com.tecgurus.puntoventa.mapper;

public interface PasswordEncodeMapper {

	/**
	 * Metodo que envia una cadena encriptada del password.
	 * 
	 * @param password dato a encriptar.
	 * @return cadena encriptada.
	 **/
	String passwordEncoder(String password);

}
