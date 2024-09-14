package com.tecgurus.puntoventa.service;

import com.tecgurus.puntoventa.dto.ProductoDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.ResponseDeleteDTO;


public interface ProductoService {
	
    /**
     * Metodo que en lista todos los productos registados.
     * @return lis ta de productos.
     * **/
	ResponseDTO listarProductos();

    /**
     * Metodo para obtener el producto por su identificador.
     * @param id identificador del producto.
     * @return lista de producto.
     * **/
    ResponseDTO listaProducto(Integer id);
	
    /**
     * Metodo que agrega un producto nuevo.
     * @param producto datos del producto.
     * @return nuevo producto registrado.
     * **/
	ResponseDTO agregaProducto(ProductoDTO producto);
	
    /**
     * Metodo que actualiza un producto existente.
     * @param producto datos del producto ha actualizar.
     * @param idProducto identificador del producto.
     * @return un producto actualizado.
     * **/
	ResponseDTO actualizaProducto(ProductoDTO producto, Integer idProducto);
	
    /**
     * Metodo que elimina un producto registrado.
     * @param idProducto identificador del producto.
     * @return respuesta success o error.
     * **/
	ResponseDeleteDTO eliminaProducto(Integer idProducto);
	
    /**
     * Metodo para buscar el producto por su nombre.
     * @param nombreProducto nombre del producto.
     * @return un producto o un null.
     * **/
    ResponseDTO busquedaProducto(String nombreProducto);

}
