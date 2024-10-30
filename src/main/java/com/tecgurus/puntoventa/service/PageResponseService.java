package com.tecgurus.puntoventa.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tecgurus.puntoventa.dto.PageResponseDTO;

public interface PageResponseService {

	/**
	 * Metodo para crear cuerpo de paginacion de datos.
	 * 
	 * @param page  datos de la paginacion.
	 * @param lista lista de los datos ya filtrados.
	 * @return un lista llena o vacion paginada.
	 **/
	PageResponseDTO paginacionDTO(Page<?> page, List<?> lista);
}
