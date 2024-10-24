package com.tecgurus.puntoventa.mapper.mapperimp;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tecgurus.puntoventa.mapper.PasswordEncodeMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PasswordEncodeMapperImp implements PasswordEncodeMapper {

	private PasswordEncoder passEncoder;

	@Override
	public String passwordEncoder(final String password) {
		return passEncoder.encode(password);
	}
}
