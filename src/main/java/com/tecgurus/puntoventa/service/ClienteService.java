package com.tecgurus.puntoventa.service;

import java.util.List;

import com.tecgurus.puntoventa.dto.ClienteDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;

public interface ClienteService {

	/***
	 * Listar todos los clientes registrados.
	 * @return lista de clientes.
	 */
	List<ClienteDTO> obtenerClientes();
	
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
	ResponseDTO eliminarCliente(Integer idCliente);
	
	
	/***
	 * Busqueda de clientes por nombre.
	 * @param nombre del cliente a buscar.
	 * @return Lista de clientes.
	 */
	List<ClienteDTO> buscaClienteNombre(String nombre);
	
	
}
