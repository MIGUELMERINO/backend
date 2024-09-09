package com.tecgurus.puntoventa.mapper;

import com.tecgurus.puntoventa.dto.UsuarioDTO;
import com.tecgurus.puntoventa.entity.Usuario;

public interface UsuarioMapper {
    
    /**
     * Metodo que realiza el cambio en entidad a DTO.
     * @param usuario datos de la entidad.
     * @return objeto de la clase DTO.
     * **/
    UsuarioDTO usuarioDTO(Usuario usuario);

    /**
     * Metodo para realizar el cambio de DTO a una entidad.
     * @param usuarioDTO dato del dto.
     * @return objeto de tipo entidad.
     * **/
    Usuario usuarioEntity(UsuarioDTO usuarioDTO);

}

