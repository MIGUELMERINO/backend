package com.tecgurus.puntoventa.service.serviceimp;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.tecgurus.puntoventa.config.Constantes;
import com.tecgurus.puntoventa.dto.CategoriaDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.ResponseDeleteDTO;
import com.tecgurus.puntoventa.entity.Categoria;
import com.tecgurus.puntoventa.mapper.CategoriaMapper;
import com.tecgurus.puntoventa.repository.CategoriaRepository;
import com.tecgurus.puntoventa.service.CategoriaService;
import com.tecgurus.puntoventa.service.PageResponseService;
import com.tecgurus.puntoventa.service.ResponseService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service // paquete o notacion que sirve para que el core de spring boot pueda tomarlo
			// como el modelo de negocios o logica del negocio.
@AllArgsConstructor
public class CategoriaServiceImp implements CategoriaService {

	// injeccion mediante constructor, ocupando lombok.
	private CategoriaRepository categoriaR;
	private CategoriaMapper categoriaMapper;
	private ResponseService responseService;
	private PageResponseService pageResponseService;

	/**
	 * Metodo que me genera una lista con las categorias existentes. Metodo que
	 * realiza un select * from de la tabla categoria
	 * 
	 * @param pageNo   numero de pagina.
	 * @param pageSize total de elementos a mostrar.
	 * @return lista de categorias.
	 */
	@Override
	public ResponseDTO listaCategorias(final Integer pageNo, final Integer pageSize) {
		Page<Categoria> cat = categoriaR.findAll(PageRequest.of(pageNo, pageSize));
		return responseService.response(Constantes.SUCCESS_READ, pageResponseService.paginacionDTO(cat,
				cat.getContent().stream().map(categoriaMapper::categoriaDTO).toList()));
	}

	/**
	 * Listar categorias mediante su identificador.
	 * 
	 * @param id identificador de la categoria.
	 * @return una lista de categoria por Identificador.
	 **/
	@Override
	public ResponseDTO listaCategoriaId(final Integer id) {
		return responseService.response(Constantes.SUCCESS_READ,
				categoriaR.findById(id).stream().map(categoriaMapper::categoriaDTO).toList());
	}

	/**
	 * Metodo que va a registrar una categoria nueva.
	 * 
	 * @param categoria datos enviados por el usuario para crear una categoria
	 *                  nueva.
	 * @return una categoria registrada existosamente.
	 */
	@Override
	public ResponseDTO agregaCategoria(final CategoriaDTO categoria) {
		return responseService.response(Constantes.SUCCESS_READ,
				categoriaMapper.categoriaDTO(categoriaR.save(categoriaMapper.categoriaEntity(categoria))));
	}

	/***
	 * Metodo de actualizacion de categoria.
	 * 
	 * @param categoria   datos a actualizar
	 * @param idCategoria identificador de categoria
	 * @return categoria actualizada.
	 */
	@Override
	public ResponseDTO actualizarCategoria(final CategoriaDTO categoria, final Integer idCategoria) {
		Categoria cate = categoriaR.findById(idCategoria)
				.orElseThrow(() -> new EntityNotFoundException(Constantes.ERROR));
		cate.setNombre(categoria.getNombre());
		cate.setDescripcion(categoria.getDescripcion());
		return responseService.response(Constantes.SUCCESS_UPDATE, categoriaMapper.categoriaDTO(categoriaR.save(cate)));
	}

	/***
	 * Metodo que elimina una categoria.
	 * 
	 * @param idCategoria identificador de la categoria.
	 * @return retorna una respuesta ok.
	 */
	@Override
	public ResponseDeleteDTO eliminarCategoria(final Integer idCategoria) {
		categoriaR.findById(idCategoria).orElseThrow(() -> new EntityNotFoundException(Constantes.ERROR));
		categoriaR.deleteById(idCategoria);
		return responseService.responseDelete(Constantes.SUCCESS_DELETE);
	}

	/**
	 * Metodo de busqueda de catetoria por nombre
	 * 
	 * @param nombre el nombre a buscar.
	 * @return lista de categorias encontradas.
	 */
	@Override
	public ResponseDTO busquedaCategoria(final String nombre) {
		return responseService.response(Constantes.SUCCESS_READ, categoriaR.findAll().stream()
				.filter(item -> item.getNombre().equalsIgnoreCase(nombre)).map(categoriaMapper::categoriaDTO).toList());
	}

}
