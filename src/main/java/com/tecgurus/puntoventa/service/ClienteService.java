package com.tecgurus.puntoventa.service;

import java.util.List;

import com.tecgurus.puntoventa.dto.ClienteDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.ResponseDeleteDTO;

public interface ClienteService {

	/***
	 * Listar todos los clientes registrados.
	 * @return lista de clientes.
	 */
	List<ClienteDTO> obtenerClientes();

    /**
     * Metodo para obtener el cliente mediante su Id.
     * @param id identificador del cliente.
     * @return cliente.
     * **/
    List<ClienteDTO> listaClienteId(Integer id);

	/**
	 * Registro de un cliente nuevo.
	 * @param cliente datos del cliente.
	 * @return cliente nuevo.
	 */
	ClienteDTO agregaCliente(ClienteDTO cliente);
	
	/***
	 * Actualiza un cliente existente.
	 * @param cliente datos a actualizar del cliente.
	 * @param idCliente identificador del cliente.
	 * @return un cliente actualizado.
	 */
	ResponseDTO actualizarCliente(ClienteDTO cliente, Integer idCliente);
	
	
	/***
	 * Elimina un cliente.
	 * @param idCliente identificador del cliente.
	 * @return respuesta correcta o error.
	 */
	ResponseDeleteDTO eliminarCliente(Integer idCliente);
	
	
	/***
	 * Busqueda de clientes por nombre.
	 * @param nombre del cliente a buscar.
	 * @return Lista de clientes.
	 */
	List<ClienteDTO> buscaClienteNombre(String nombre);
	
	
}
