package com.tecgurus.puntoventa.service.serviceimp;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.tecgurus.puntoventa.dto.PageResponseDTO;
import com.tecgurus.puntoventa.service.PageResponseService;

@Service
public class PageResponseServiceImp implements PageResponseService {

	/**
	 * Metodo que se implementa para crear el cuerpo de la paginacion.
	 * 
	 * @param page listado que resulta del paginado.
	 * @return estructura del DTO page response.
	 **/
	@Override
	public PageResponseDTO paginacionDTO(final Page<?> page, final List<?> lista) {
		PageResponseDTO pageResponseDTO = new PageResponseDTO();
		pageResponseDTO.setBody(lista);
		pageResponseDTO.setPageNo(page.getNumber());
		pageResponseDTO.setPageSize(page.getContent().size());
		pageResponseDTO.setTotalElements(page.getTotalElements());
		pageResponseDTO.setTotalPages(page.getTotalPages());
		pageResponseDTO.setLast(page.isLast());
		return pageResponseDTO;
	}
}
