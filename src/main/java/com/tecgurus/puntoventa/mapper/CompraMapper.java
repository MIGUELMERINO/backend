package com.tecgurus.puntoventa.mapper;

import com.tecgurus.puntoventa.dto.CompraDTO;
import com.tecgurus.puntoventa.entity.Compra;


public interface CompraMapper {
    
    /**
     * Metodo que realiza el cambio de entidad a DTO.
     * @param compra datos de la entidad.
     * @return objeto de la clase DTO.
     * **/
    CompraDTO compraDTO(Compra compra);

    /**
     * Metodo para realizar el cambio de DTO a una entidad.
     * @param compraDTO datos del dto.
     * @return objeto de tipo entidad.
     * **/
    Compra compraEntity(CompraDTO compraDTO);
}


