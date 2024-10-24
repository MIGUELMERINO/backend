package com.tecgurus.puntoventa.mapper;

import com.tecgurus.puntoventa.dto.ClienteDTO;
import com.tecgurus.puntoventa.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mapping;
import org.mapstruct.InheritConfiguration;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {

	/**
	 * Metodo que realiza el cambio de entidad a DTO.
	 * 
	 * @param cliente datos del cliente.
	 * @return objeto de la clase DTO.
	 **/
	@Mapping(source = "idCliente", target = "clave")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "apaterno", target = "apellidoP")
	@Mapping(source = "amaterno", target = "apellidoM")
	@Mapping(source = "rfc", target = "rfc")
	ClienteDTO clienteDTO(Cliente cliente);

	/**
	 * Metodo para realizar el cambio de DTO a una entidad.
	 * 
	 * @param clienteDTO datos del dto.
	 * @return objeto de tipo entidad.
	 **/
	@InheritConfiguration
	Cliente clienteEntity(ClienteDTO clienteDTO);

}
