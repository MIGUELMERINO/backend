package com.tecgurus.puntoventa.mapper;

import com.tecgurus.puntoventa.entity.Categoria;
import com.tecgurus.puntoventa.dto.CategoriaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoriaMapper {

	/**
	 * Metodo que realiza el cambio de entidad a DTO.
	 * 
	 * @param categoria datos de la entidad
	 * @return objeto de la clase DTO.
	 **/
	@Mapping(source = "idCategoria", target = "clave")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "descripcion", target = "descripcion")
	CategoriaDTO categoriaDTO(Categoria categoria);

	/**
	 * Metodo para realizar el cambio de DTO a una entidad.
	 * 
	 * @param categoriaDTO dato del dto.
	 * @return objeto de tipo entidad.
	 **/
	@InheritInverseConfiguration
	Categoria categoriaEntity(CategoriaDTO categoriaDTO);
}
