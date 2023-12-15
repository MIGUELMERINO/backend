package com.tecgurus.puntoventa.service;

import java.util.List;

import com.tecgurus.puntoventa.dto.CompraProductoDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;

public interface CompraProductoService {
	
	/***
	 * 
	 * @return
	 */
	List<CompraProductoDTO> listarComprasProductos();
	
	/***
	 * 
	 * @param compraProducto
	 * @return
	 */
	CompraProductoDTO agregaCompraProducto(CompraProductoDTO compraProducto);
	
	/***
	 * 
	 * @param idCompraProducto
	 * @param compraProducto
	 * @return
	 */
	CompraProductoDTO actualizarCompraProducto(Integer idCompraProducto, CompraProductoDTO compraProducto);
	
	/***
	 * 
	 * @param idCompra
	 * @return
	 */
	ResponseDTO busquedaCompra(Integer idCompra);

}
