package com.tecgurus.puntoventa.service;

import com.tecgurus.puntoventa.dto.CompraProductoDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;

public interface CompraProductoService {

	/***
	 * Lista de todas las compras registradas.
	 * 
	 * @param pageNo   numero de pagina.
	 * @param pageSize total de elementos a mostarar.
	 * @return lista de compras.
	 */
	ResponseDTO listarComprasProductos(Integer pageNo, Integer pageSize);

	/**
	 * Metodo para mostar la compra y el producto por su identificador.
	 * 
	 * @param id identificador de la compra.
	 * @return registro de compra por identificador.
	 **/
	ResponseDTO listarCompraProducto(Integer id);

	/***
	 * agregar una nueva compra.
	 * 
	 * @param compraProducto datos de la compra.
	 * @return una nueva compra.
	 */
	ResponseDTO agregaCompraProducto(CompraProductoDTO compraProducto);

	/***
	 * Busqueda de la compra mediante el identificador.
	 * 
	 * @param idCompra identificador de la compra.
	 * @return valor de la busqueda.
	 */
	ResponseDTO busquedaCompra(Integer idCompra);

}
