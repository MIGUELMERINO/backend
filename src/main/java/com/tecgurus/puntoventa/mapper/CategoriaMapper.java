package com.tecgurus.puntoventa.mapper;

import com.tecgurus.puntoventa.entity.Categoria;
import com.tecgurus.puntoventa.dto.CategoriaDTO;


public interface CategoriaMapper {
    
    /**
     * Metodo que realiza el cambio de entidad a DTO.
     * @param categoria datos de la entidad 
     * @return objeto de la clase DTO.
     * **/
    CategoriaDTO categoriaDTO(Categoria categoria);


    /**
     * Metodo para realizar el cambio de DTO a una entidad.
     * @param categoriaDTO dato del dto.
     * @return objeto de tipo entidad.
     * **/
    Categoria categoriaEntity(CategoriaDTO categoriaDTO);
}

