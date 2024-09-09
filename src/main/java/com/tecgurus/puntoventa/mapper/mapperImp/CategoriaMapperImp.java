package com.tecgurus.puntoventa.mapper.mapperImp;

import com.tecgurus.puntoventa.mapper.CategoriaMapper;
import org.springframework.stereotype.Service;

import com.tecgurus.puntoventa.dto.CategoriaDTO;
import com.tecgurus.puntoventa.entity.Categoria;


@Service 
public class CategoriaMapperImp implements CategoriaMapper {
   
    /***
	 * Metodo que convierte de entidad - dto.
	 * @param entidad datos 
	 * @return un dto.
	 */

    @Override
    public CategoriaDTO categoriaDTO(final Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
		dto.setClave(categoria.getIdCategoria());
		dto.setNombre(categoria.getNombre());
		dto.setDescripcion(categoria.getDescripcion());
		return dto;

    }

	/***
	 * Metodo que convierte de DTO - Etidad.
	 * @param dto datos enviados.
	 * @return entindad.
	 */
    @Override 
    public Categoria categoriaEntity(CategoriaDTO categoriaDTO) {
        Categoria entidad = new Categoria();
		entidad.setIdCategoria(categoriaDTO.getClave()); // null -> nuevo, si no es null hay que actualizar.
		entidad.setNombre(categoriaDTO.getNombre());
		entidad.setDescripcion(categoriaDTO.getDescripcion());
		return entidad;
    }

}

