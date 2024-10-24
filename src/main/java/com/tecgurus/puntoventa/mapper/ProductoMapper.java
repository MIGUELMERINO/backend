package com.tecgurus.puntoventa.mapper;

import com.tecgurus.puntoventa.dto.ProductoDTO;
import com.tecgurus.puntoventa.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mapping;
import org.mapstruct.InheritConfiguration;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { CategoriaMapper.class })
public interface ProductoMapper {

	/**
	 * Metodo que realiza el cambio de entidad a DTO.
	 * 
	 * @param producto datos de la entidad
	 * @return objeto de la clase DTO.
	 **/
	@Mapping(source = "idProducto", target = "clave")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "descripcion", target = "descripcion")
	@Mapping(source = "precio", target = "precio")
	@Mapping(source = "stock", target = "stock")
	@Mapping(source = "sku", target = "sku")
	@Mapping(source = "imagen", target = "imagen")
	ProductoDTO productoDTO(Producto producto);

	/**
	 * Metodo para realizar el cambio de DTO a una entidad.
	 * 
	 * @param productoDTO datos del dto.
	 * @return objeto de tipo entidad.
	 **/
	@InheritConfiguration
	Producto productoEntity(ProductoDTO productoDTO);

}
