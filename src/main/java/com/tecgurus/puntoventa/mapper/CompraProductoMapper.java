package com.tecgurus.puntoventa.mapper;

import com.tecgurus.puntoventa.dto.CompraProductoDTO;
import com.tecgurus.puntoventa.entity.CompraProducto;


public interface CompraProductoMapper {

    /**
     * Metodo que realiza el cambio en la entidad a DTO.
     * @param compraProducto datos de la entidad.
     * @return objeto de la clase DTO.
     * **/
    CompraProductoDTO compraProductoDTO(CompraProducto compraProducto);

    /**
     * Metodo para la realizar el cambio de DTO a entidad.
     * @param compraProductoDTO datos del dto.
     * @return objeto de tipo entidad.
     * **/
    CompraProducto compraProductoEntity(CompraProductoDTO compraProductoDTO);

}

