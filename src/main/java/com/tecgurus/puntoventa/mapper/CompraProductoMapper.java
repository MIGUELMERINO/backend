package com.tecgurus.puntoventa.mapper;

import com.tecgurus.puntoventa.dto.CompraProductoDTO;
import com.tecgurus.puntoventa.entity.CompraProducto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.InheritConfiguration;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { CompraMapper.class, ProductoMapper.class })
public interface CompraProductoMapper {

    /**
     * Metodo que realiza el cambio en la entidad a DTO.
     * @param compraProducto datos de la entidad.
     * @return objeto de la clase DTO.
     * **/
    @Mapping(source = "idCompraProducto", target = "clave")
    @Mapping(source = "cantidad", target = "cantidad")
    @Mapping(source = "costo", target = "costo")
    CompraProductoDTO compraProductoDTO(CompraProducto compraProducto);

    /**
     * Metodo para la realizar el cambio de DTO a entidad.
     * @param compraProductoDTO datos del dto.
     * @return objeto de tipo entidad.
     * **/
    @InheritConfiguration
    CompraProducto compraProductoEntity(CompraProductoDTO compraProductoDTO);

}

