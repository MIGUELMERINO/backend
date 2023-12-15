package com.tecgurus.puntoventa.service;

import java.util.List;
import com.tecgurus.puntoventa.dto.ProductoDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;


public interface ProductoService {
	
	List<ProductoDTO> listarProductos();
	
	ProductoDTO agregaProducto(ProductoDTO producto);
	
	ProductoDTO actualizaProducto(ProductoDTO producto, Integer idProducto);
	
	ResponseDTO eliminaProducto(Integer idProducto);
	
	List<ProductoDTO> busquedaProducto(String nombreProducto);

}
