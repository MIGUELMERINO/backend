package com.tecgurus.puntoventa.service;

import com.tecgurus.puntoventa.dto.CompraDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;

public interface CompraService {

	/***
	 * Lista de todas las compras.
	 * 
	 * @return lista de todas las compras existentes.
	 */
	ResponseDTO listarCompras();

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
