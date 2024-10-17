package com.tecgurus.puntoventa.mapper;

import com.tecgurus.puntoventa.dto.CompraDTO;
import com.tecgurus.puntoventa.entity.Compra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.InheritInverseConfiguration;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { UsuarioMapper.class, ClienteMapper.class })
public interface CompraMapper {
    
    /**
     * Metodo que realiza el cambio de entidad a DTO.
     * @param compra datos de la entidad.
     * @return objeto de la clase DTO.
     * **/
    @Mapping(source = "idCompra", target = "clave")
    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "total", target = "total")
    CompraDTO compraDTO(Compra compra);

    /**
     * Metodo para realizar el cambio de DTO a una entidad.
     * @param compraDTO datos del dto.
     * @return objeto de tipo entidad.
     * **/
    @InheritInverseConfiguration
    Compra compraEntity(CompraDTO compraDTO);
}


