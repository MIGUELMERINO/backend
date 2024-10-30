package com.tecgurus.puntoventa.service;

import com.tecgurus.puntoventa.dto.CompraDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;

public interface CompraService {

	/***
	 * Lista de todas las compras.
	 * 
	 * @param pageNo   numero de pagina.
	 * @param pageSize total de elementos a mostrar.
	 * @return lista de todas las compras existentes.
	 */
	ResponseDTO listarCompras(Integer pageNo, Integer pageSize);

	/**
	 * Metodo que agrega una compra nueva.
	 * 
	 * @param compra datos para registrar la compra.
	 * @return una compra registrada.
	 */
	ResponseDTO agregaCompra(CompraDTO compra);

	/***
	 * Metodo que busca una lista de compras por usuario.
	 * 
	 * @param idUsuario identificador del usuario.
	 * @return lista de compras por usuario.
	 */
	ResponseDTO busquedaCompraId(Integer idUsuario);

}
