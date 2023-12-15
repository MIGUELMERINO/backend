package com.tecgurus.puntoventa.service;

import java.util.List;
import com.tecgurus.puntoventa.dto.CategoriaDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;




public interface CategoriaService {
	
	/**
	 * Metodo que enlista las categorias registradas.
	 * @return lista de categorias.
	 */
	List<CategoriaDTO> listaCategorias();
	
	/***
	 * Metodo que crea una nueva categoria
	 * @param categoria datos de categoria
	 * @return una categoria registrada existosamente.
	 */
	CategoriaDTO agregaCategoria(CategoriaDTO categoria);
	
	/***
	 * Metodo que actualiza una categoria registrada.
	 * @param categoria
	 * @return categoria actualizada
	 */
	CategoriaDTO actualizarCategoria(CategoriaDTO categoria, Integer idCategoria);
	
	/***
	 * Metodo de eliminacion de categoria.
	 * @param idCategoria identificador de categoria
	 * @return response de eliminacion.
	 */
	ResponseDTO eliminarCategoria(Integer idCategoria);
	
	/***
	 * Metodo de busqueda de categoria por nombre
	 * @param nombre de categoria.
	 * @return lista con los nombre de la categoria.
	 */
	List<CategoriaDTO> busquedaCategoria(String nombre);
}
