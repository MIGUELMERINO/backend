package com.tecgurus.puntoventa.service.serviceimp;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.tecgurus.puntoventa.config.Constantes;
import com.tecgurus.puntoventa.dto.ClienteDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.ResponseDeleteDTO;
import com.tecgurus.puntoventa.entity.Cliente;
import com.tecgurus.puntoventa.mapper.ClienteMapper;
import com.tecgurus.puntoventa.repository.ClienteRepository;
import com.tecgurus.puntoventa.service.ClienteService;
import com.tecgurus.puntoventa.service.PageResponseService;
import com.tecgurus.puntoventa.service.ResponseService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteServiceImp implements ClienteService {

	private ClienteRepository clienteRepository;
	private ClienteMapper clienteMapper;
	private ResponseService responseService;
	private PageResponseService pageResponseService;

	/**
	 * Lista de clientes.
	 * 
	 * @param pageNo   numnero de pagina.
	 * @param pageSize numero de elementos a mostrar.
	 * @return lista de clientes
	 */
	@Override
	public ResponseDTO obtenerClientes(final Integer pageNo, final Integer pageSize) {
		// :: asingacion de metodo en un map. instancia::metodo
		Page<Cliente> client = clienteRepository.findAll(PageRequest.of(pageNo, pageSize));
		return responseService.response(Constantes.SUCCESS_READ, pageResponseService.paginacionDTO(client,
				client.getContent().stream().map(clienteMapper::clienteDTO).toList()));
	}

	/**
	 * Buscar un cliente mediante el identificador.
	 * 
	 * @param id identificador del cliente.
	 * @return cliente o un arreglo vacio.
	 **/
	@Override
	public ResponseDTO listaClienteId(final Integer id) {
		return responseService.response(Constantes.SUCCESS_READ,
				clienteRepository.findById(id).stream().map(clienteMapper::clienteDTO).toList());
	}

	/**
	 * Registro de un cliente nuevo.
	 * 
	 * @param clienteDTO datos del cliente.
	 * @return retorna un cliente nuevo.
	 */
	@Override
	public ResponseDTO agregaCliente(final ClienteDTO clienteDTO) {
		return responseService.response(Constantes.SUCCESS_CREATE,
				clienteMapper.clienteDTO(clienteRepository.save(clienteMapper.clienteEntity(clienteDTO))));
	}

	/**
	 * actualiza un cliente existente.
	 * 
	 * @param cliente   datos del cliente.
	 * @param idCliente identificador del cliente.
	 * @return valores actualizados.
	 */
	@Override
	public ResponseDTO actualizarCliente(final ClienteDTO cliente, final Integer idCliente) {
		Cliente clienteE = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new EntityNotFoundException(Constantes.ERROR));
		clienteE.setAmaterno(cliente.getApellidoM());
		clienteE.setApaterno(cliente.getApellidoP());
		clienteE.setRfc(cliente.getRfc());
		return responseService.response(Constantes.SUCCESS_UPDATE,
				clienteMapper.clienteDTO(clienteRepository.save(clienteE)));
	}

	/**
	 * elimnar un cliente existente.
	 * 
	 * @param idCliente identificador del cliente.
	 * @return respuesta ok o error.
	 */
	@Override
	public ResponseDeleteDTO eliminarCliente(final Integer idCliente) {
		clienteRepository.findById(idCliente);
		clienteRepository.deleteById(idCliente);
		return responseService.responseDelete(Constantes.SUCCESS_DELETE);
	}

	/***
	 * Metodo que realiza una busqueda por el nombre completo del cliente.
	 * 
	 * @param nombre nombre completo de cliente.
	 * @return lista de los clientes con el mismo nombre.
	 */
	@Override
	public ResponseDTO buscaClienteNombre(final String nombre) {
		return responseService.response(Constantes.SUCCESS_READ, clienteRepository.findAll().stream()
				.filter(item -> item.getNombre().equalsIgnoreCase(nombre)).map(clienteMapper::clienteDTO).toList());
	}

}
