package com.tecgurus.puntoventa.service.serviceImp;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tecgurus.puntoventa.dto.CategoriaDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.entity.Categoria;
import com.tecgurus.puntoventa.repository.CategoriaRepository;
import com.tecgurus.puntoventa.service.CategoriaService;
import com.tecgurus.puntoventa.mapper.CategoriaMapper;

import lombok.AllArgsConstructor;

@Service  // paquete o notacion que sirve para que el core de spring boot pueda tomarlo como el modelo de negocios o logica del negocio.
@AllArgsConstructor
public class CategoriaServiceImp implements CategoriaService{

	
	private CategoriaRepository categoriaR;
	private CategoriaMapper categoriaMapper;
	
	
	/**
	 * Metodo que me genera una lista con las categorias existentes.
	 * Metodo que realiza un select * from de la tabla categoria
	 * @return lista de categorias.
	 */
	@Override
	public List<CategoriaDTO> listaCategorias() {
        // return categoriaR.findAll().stream().map(categoriaMapper::categoriaDTO).collect(Collectors.toList());
        return categoriaR.findAll().stream().map(categoriaMapper::categoriaDTO).collect(Collectors.toList());
	}

	/**
	 * Metodo que va a registrar una categoria nueva.
     * @param categoria datos enviados por el usuario para crear una categoria nueva.
	 * @return una categoria registrada existosamente.
	 */
	@Override
	public CategoriaDTO agregaCategoria(final CategoriaDTO categoria) {
		return categoriaMapper.categoriaDTO(categoriaR.save(categoriaMapper.categoriaEntity(categoria)));
	}

	/***
	 * Metodo de actualizacion de categoria.
	 * @param categoria datos a actualizar
	 * @param idCategoria identificador de categoria
	 * @return categoria actualizada.
	 */
	@Override
	public CategoriaDTO actualizarCategoria(final CategoriaDTO categoria, final Integer idCategoria) {
		Categoria cate = categoriaR.busquedaPorId(idCategoria);
		if (cate != null) {
			cate.setNombre(categoria.getNombre());
			cate.setDescripcion(categoria.getDescripcion());
		}
		return categoriaMapper.categoriaDTO(categoriaR.save(cate));
	}

	/***
	 * Metodo que elimina una categoria.
	 * @param idCategoria identificador de la categoria.
	 * @return retorna una respuesta ok.
	 */
	@Override
	public ResponseDTO eliminarCategoria(final Integer idCategoria) {
		Categoria categoria = categoriaR.findByIds(idCategoria);
		ResponseDTO dto = new ResponseDTO();
		if (categoria != null) {
			dto.setClave("200");
			dto.setValor("Categoria eliminada con exito!");
			categoriaR.deleteById(idCategoria);
			// delete from categoria where idcategoria = 1;
		}
		return dto;
	}

	/**
	 * Metodo de busqueda de catetoria por nombre
	 * @param nombre el nombre a buscar.
	 * @return lista de categorias encontradas.
	 */
	@Override
	public List<CategoriaDTO> busquedaCategoria(final String nombre) {
		// stream forma de recoleccion de datos, map (mapeo de datos), genera una coleccion de datos y se el asignan a una lista.
		return categoriaR.findByNombre(nombre).stream().map(categoriaMapper::categoriaDTO).collect(Collectors.toList());
	}

	
	

	
}
