package com.tecgurus.puntoventa.mapper.mapperImp;

import com.tecgurus.puntoventa.mapper.ClienteMapper;
import com.tecgurus.puntoventa.dto.ClienteDTO;
import com.tecgurus.puntoventa.entity.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapperImp implements ClienteMapper {
   
	/***
	 * Metodo que convierte de Entidad a DTO.
	 * @param cliente datos del cliente.
	 * @return un dto
	 */
    @Override
    public ClienteDTO clienteDTO(Cliente cliente) {        
        ClienteDTO dto = new ClienteDTO();
		dto.setClave(cliente.getIdCliente());
		dto.setNombre(cliente.getNombre());
		dto.setApellidoP(cliente.getApaterno());
		dto.setApellidoM(cliente.getAmaterno());
		dto.setRfc(cliente.getRfc());
		return dto;
    }

    /***
	 * Metodo que convierte DTO a Entidad.
	 * @param dto datos del DTO
	 * @return retorna una entidad.
	 */
    @Override
    public Cliente clienteEntity(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
		cliente.setIdCliente(clienteDTO.getClave());
		cliente.setNombre(clienteDTO.getNombre());
		cliente.setApaterno(clienteDTO.getApellidoP());
		cliente.setAmaterno(clienteDTO.getApellidoM());
		cliente.setRfc(clienteDTO.getRfc());
		return cliente;
    }

}



