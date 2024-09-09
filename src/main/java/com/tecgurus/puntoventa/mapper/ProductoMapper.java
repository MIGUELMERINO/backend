package com.tecgurus.puntoventa.mapper;

import com.tecgurus.puntoventa.dto.ProductoDTO;
import com.tecgurus.puntoventa.entity.Producto;


public interface ProductoMapper {
    
    /**
     * Metodo que realiza el cambio de entidad a DTO.
     * @param producto datos de la entidad
     * @return objeto de la clase DTO.
     * **/
    ProductoDTO productoDTO(Producto producto);

    /**
     * Metodo para realizar el cambio de DTO a una entidad.
     * @param productoDTO datos del dto.
     * @return objeto de tipo entidad.
     * **/
    Producto productoEntity(ProductoDTO productoDTO);

}



