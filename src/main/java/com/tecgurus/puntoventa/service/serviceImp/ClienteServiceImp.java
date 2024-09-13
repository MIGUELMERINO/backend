package com.tecgurus.puntoventa.service.serviceImp;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.tecgurus.puntoventa.dto.ClienteDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.ResponseDeleteDTO;
import com.tecgurus.puntoventa.entity.Cliente;
import com.tecgurus.puntoventa.repository.ClienteRepository;
import com.tecgurus.puntoventa.service.ClienteService;
import com.tecgurus.puntoventa.service.ResponseService;
import com.tecgurus.puntoventa.mapper.ClienteMapper;
import com.tecgurus.puntoventa.config.Constantes;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteServiceImp implements ClienteService {
	
	private ClienteRepository clienteRepository;
	private ClienteMapper clienteMapper;
    @Autowired
    private ResponseService responseService;


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
     * Buscar un cliente mediante el identificador.
     * @param id identificador del cliente.
     * @return cliente o un arreglo vacio.
     * **/
    @Override
    public List<ClienteDTO> listaClienteId(final Integer id) {
        return clienteRepository.findById(id).stream().map(clienteMapper::clienteDTO).collect(Collectors.toList());
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
				.orElseThrow(() -> new EntityNotFoundException(Constantes.ERROR));
		return responseService.response(Constantes.SUCCESS_UPDATE, 
        clienteMapper.clienteDTO(clienteRepository.save(clienteE)));
	}

	/**
	 * elimnar un cliente existente.
	 * @param idCliente identificador del cliente.
	 * @return respuesta ok o error.
	 */
	@Override
	public ResponseDeleteDTO eliminarCliente(final Integer idCliente) {
		clienteRepository.findById(idCliente)
				.orElseThrow(() -> new EntityNotFoundException(Constantes.ERROR));
		clienteRepository.deleteById(idCliente);
        return responseService.responseDelete(Constantes.SUCCESS_DELETE);
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
