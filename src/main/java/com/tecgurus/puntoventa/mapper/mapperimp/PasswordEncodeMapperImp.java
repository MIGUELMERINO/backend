package com.tecgurus.puntoventa.mapper.mapperimp;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tecgurus.puntoventa.mapper.PasswordEncodeMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PasswordEncodeMapperImp implements PasswordEncodeMapper {

	private PasswordEncoder passEncoder;

	/**
	 * Metodo que crea un password codificado
	 * 
	 * @param password texto usado para crear el password codificado
	 * @return password codificado
	 **/
	@Override
	public String passwordEncoder(final String password) {
		return passEncoder.encode(password);
	}
}
