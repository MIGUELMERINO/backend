package com.tecgurus.puntoventa.mapper;

import com.tecgurus.puntoventa.dto.ClienteDTO;
import com.tecgurus.puntoventa.entity.Cliente;

public interface ClienteMapper {
    
    /**
     * Metodo que realiza el cambio de entidad a DTO.
     * @param cliente datos del cliente.
     * @return objeto de la clase DTO.
     * **/
    ClienteDTO clienteDTO(Cliente cliente);

    /**
     * Metodo para realizar el cambio de DTO a una entidad.
     * @param clienteDTO datos del dto.
     * @return objeto de tipo entidad.
     * **/
    Cliente clienteEntity(ClienteDTO clienteDTO);

}

