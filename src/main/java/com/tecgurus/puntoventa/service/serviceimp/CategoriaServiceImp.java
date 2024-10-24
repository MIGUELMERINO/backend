package com.tecgurus.puntoventa.service.serviceimp;


import java.util.List;
import org.springframework.stereotype.Service;
import com.tecgurus.puntoventa.config.Constantes;
import com.tecgurus.puntoventa.dto.CategoriaDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.ResponseDeleteDTO;
import com.tecgurus.puntoventa.entity.Categoria;
import com.tecgurus.puntoventa.mapper.CategoriaMapper;
import com.tecgurus.puntoventa.repository.CategoriaRepository;
import com.tecgurus.puntoventa.service.CategoriaService;
import com.tecgurus.puntoventa.service.ResponseService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service  // paquete o notacion que sirve para que el core de spring boot pueda tomarlo como el modelo de negocios o logica del negocio.
@AllArgsConstructor
public class CategoriaServiceImp implements CategoriaService{

    // injeccion mediante constructor, ocupando lombok.	
	private CategoriaRepository categoriaR;
	private CategoriaMapper categoriaMapper;
    private ResponseService responseService;
	
	
	/**
	 * Metodo que me genera una lista con las categorias existentes.
	 * Metodo que realiza un select * from de la tabla categoria
	 * @return lista de categorias.
	 */
	@Override
	public ResponseDTO listaCategorias() {
        List<CategoriaDTO> categoria =  categoriaR.findAll().stream().map(categoriaMapper::categoriaDTO).toList();
        return responseService.response(Constantes.SUCCESS_READ,categoria);
	}


    @Override
    public ResponseDTO listaCategoriaId(final Integer id) {
        List<CategoriaDTO> categoria  = categoriaR.findById(id).stream().map(categoriaMapper::categoriaDTO).toList();
        return responseService.response(Constantes.SUCCESS_READ, categoria);
    }

	/**
	 * Metodo que va a registrar una categoria nueva.
     * @param categoria datos enviados por el usuario para crear una categoria nueva.
	 * @return una categoria registrada existosamente.
	 */
	@Override
	public ResponseDTO agregaCategoria(final CategoriaDTO categoria) {
		CategoriaDTO categoriaI = categoriaMapper.categoriaDTO(categoriaR.save(categoriaMapper.categoriaEntity(categoria)));
        return responseService.response(Constantes.SUCCESS_READ, categoriaI);
	}

	/***
	 * Metodo de actualizacion de categoria.
	 * @param categoria datos a actualizar
	 * @param idCategoria identificador de categoria
	 * @return categoria actualizada.
	 */
	@Override
	public ResponseDTO actualizarCategoria(final CategoriaDTO categoria, final Integer idCategoria) {
		Categoria cate = categoriaR.findById(idCategoria)
        .orElseThrow(() -> new EntityNotFoundException(Constantes.ERROR));
		cate.setNombre(categoria.getNombre());
		cate.setDescripcion(categoria.getDescripcion());
		return responseService.response(Constantes.SUCCESS_UPDATE, 
        categoriaMapper.categoriaDTO(categoriaR.save(cate)));
	}

	/***
	 * Metodo que elimina una categoria.
	 * @param idCategoria identificador de la categoria.
	 * @return retorna una respuesta ok.
	 */
	@Override
	public ResponseDeleteDTO eliminarCategoria(final Integer idCategoria) {
		categoriaR.findById(idCategoria);
		categoriaR.deleteById(idCategoria);
		return responseService.responseDelete(Constantes.SUCCESS_DELETE);
	}

	/**
	 * Metodo de busqueda de catetoria por nombre
	 * @param nombre el nombre a buscar.
	 * @return lista de categorias encontradas.
	 */
	@Override
	public ResponseDTO busquedaCategoria(final String nombre) {
		// stream forma de recoleccion de datos, map (mapeo de datos), genera una coleccion de datos y se el asignan a una lista.
		 List<CategoriaDTO> categoria =  categoriaR.findByNombre(nombre).stream().map(categoriaMapper::categoriaDTO).toList();
        return responseService.response(Constantes.SUCCESS_READ, categoria);
	}

	
	

	
}
