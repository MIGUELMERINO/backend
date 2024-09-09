package com.tecgurus.puntoventa.service.serviceImp;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tecgurus.puntoventa.dto.ClienteDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.entity.Cliente;
import com.tecgurus.puntoventa.repository.ClienteRepository;
import com.tecgurus.puntoventa.service.ClienteService;
import com.tecgurus.puntoventa.mapper.ClienteMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteServiceImp implements ClienteService {
	
	private ClienteRepository clienteRepository;
	private ClienteMapper clienteMapper;


	/**
	 * Lista de clientes.
	 * @return lista de clientes
	 */
	@Override
	public List<ClienteDTO> obtenerClientes() {
		// :: asingacion de metodo en un map.           instancia::metodo
		return clienteRepository.findAll().stream().map(clienteMapper::clienteDTO).collect(Collectors.toList());
	}

	/**
	 * Registro de un cliente nuevo.
	 * @param clienteDTO datos del cliente.
	 * @return retorna un cliente nuevo.
	 */
	@Override
	public ClienteDTO agregaCliente(final ClienteDTO clienteDTO) {
		return clienteMapper.clienteDTO(clienteRepository.save(clienteMapper.clienteEntity(clienteDTO)));
	}

	
	/**
	 * actualiza un cliente existente.
	 * @param cliente datos del cliente.
	 * @param idCliente identificador del cliente.
	 * @return valores actualizados.
	 */
	@Override
	public ResponseDTO actualizarCliente(final ClienteDTO cliente, final Integer idCliente) {
		// findById selec * from cliente where idcliente = ?1
		Cliente clienteE = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new EntityNotFoundException("Registro no encontrado"));
		ResponseDTO dto = new ResponseDTO();
		if (clienteE != null && clienteE.getIdCliente() != null) {
			dto.setClave("200");
			clienteE.setNombre(cliente.getNombre());
			clienteE.setApaterno(cliente.getApellidoP());
			clienteE.setAmaterno(cliente.getApellidoM());
			dto.setValor("Actualizacion realizada");
			dto.setPlayLoad(clienteMapper.clienteDTO(clienteRepository.save(clienteE)));
		} else {
			dto.setClave("500");
			dto.setValor("el registro que deseas actualizar no se encuentra");
			dto.setPlayLoad(cliente);
		}
		return dto;
	}

	/**
	 * elimnar un cliente existente.
	 * @param idCliente identificador del cliente.
	 * @return respuesta ok o error.
	 */
	@Override
	public ResponseDTO eliminarCliente(final Integer idCliente) {
		ResponseDTO dto = new ResponseDTO();
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new EntityNotFoundException("Registro no encontrado"));
		if (cliente != null && cliente.getIdCliente() != null) {
			dto.setClave("200");
			dto.setValor("Cliente eliminado correctamente!");
			clienteRepository.deleteById(idCliente);
		} else {
			dto.setClave("500");
			dto.setValor("el registro que deseas eliminar no se encuentra");
		}
		return dto;
	}

	/***
	 * Metodo que realiza una busqueda por el nombre completo del cliente.
	 * @param nombre nombre completo de cliente.
	 * @return lista de los clientes con el mismo nombre.
	 */
	@Override
	public List<ClienteDTO> buscaClienteNombre(final String nombre) {
		return clienteRepository.findByNombre(nombre).stream().map(clienteMapper::clienteDTO).collect(Collectors.toList());
	}

}
