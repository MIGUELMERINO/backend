package com.tecgurus.puntoventa.service;

import java.util.List;
import com.tecgurus.puntoventa.dto.CompraDTO;


public interface CompraService {
	
	/***
	 * Lista de todas las compras.
	 * @return
	 */
	List<CompraDTO> listarCompras();
	
	/**
	 * Metodo que agrega una compra nueva.
	 * @param compra datos para registrar la compra.
	 * @return una compra registrada.
	 */
	CompraDTO agregaCompra(CompraDTO compra);
	
	/**
	 * Metodo para actualizar la compra.
	 * @param compra datos para actualizar la compra.
	 * @param idCompra identificador de la compra.
	 * @return una compra actualizada.
	 */
	CompraDTO actualizaCompra(CompraDTO compra, Integer idCompra);
	
	/***
	 * Metodo que busca una lista de compras por usuario.
	 * @param idUsuario identificador del usuario.
	 * @return lista de compras por usuario.
	 */
	List<CompraDTO> busquedaCompraId(Integer idUsuario);

	
}
