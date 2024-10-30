package com.tecgurus.puntoventa.service;

import com.tecgurus.puntoventa.dto.CategoriaDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.ResponseDeleteDTO;

public interface CategoriaService {

	/**
	 * Metodo que enlista las categorias registradas.
	 * 
	 * @param pageNo   numero de pagina,
	 * @param pageSize total de elementos a mostrar.
	 * @return lista de categorias.
	 */
	ResponseDTO listaCategorias(Integer pageNo, Integer pageSize);

	/**
	 * Metodo para obtener la categoria mediante ID.
	 * 
	 * @param id Identificador de la categoria.
	 * @return una categoria.
	 **/
	ResponseDTO listaCategoriaId(Integer id);

	/***
	 * Metodo que crea una nueva categoria
	 * 
	 * @param categoria datos de categoria
	 * @return una categoria registrada existosamente.
	 */
	ResponseDTO agregaCategoria(CategoriaDTO categoria);

	/***
	 * Metodo que actualiza una categoria registrada.
	 * 
	 * @param categoria
	 * @return categoria actualizada
	 */
	ResponseDTO actualizarCategoria(CategoriaDTO categoria, Integer idCategoria);

	/***
	 * Metodo de eliminacion de categoria.
	 * 
	 * @param idCategoria identificador de categoria
	 * @return response de eliminacion.
	 */
	ResponseDeleteDTO eliminarCategoria(Integer idCategoria);

	/***
	 * Metodo de busqueda de categoria por nombre
	 * 
	 * @param nombre de categoria.
	 * @return lista con los nombre de la categoria.
	 */
	ResponseDTO busquedaCategoria(String nombre);
}
