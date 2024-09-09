package com.tecgurus.puntoventa.mapper.mapperImp;

import com.tecgurus.puntoventa.mapper.PasswordEncodeMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service 
public class PasswordEncodeMapperImp implements PasswordEncodeMapper {
    
    @Autowired
    private PasswordEncoder passEncoder;

    @Override
    public String passwordEncoder(final String password) {
        return passEncoder.encode(password);
    }
}


