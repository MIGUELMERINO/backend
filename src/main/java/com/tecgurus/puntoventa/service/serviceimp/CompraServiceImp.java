package com.tecgurus.puntoventa.service.serviceimp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tecgurus.puntoventa.config.Constantes;
import com.tecgurus.puntoventa.dto.CompraDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.entity.Compra;
import com.tecgurus.puntoventa.mapper.CompraMapper;
import com.tecgurus.puntoventa.repository.CompraRepository;
import com.tecgurus.puntoventa.service.CompraService;
import com.tecgurus.puntoventa.service.PageResponseService;
import com.tecgurus.puntoventa.service.ResponseService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompraServiceImp implements CompraService {

	private CompraRepository compraRepository;
	private CompraMapper compraMapper;
	private ResponseService responseService;
	private PageResponseService pageResponseService;

	/***
	 * Lista de compras.
	 * 
	 * @param pageNo   numero de pagina.
	 * @param pageSize total de elementos a mostrar.
	 * @return Lista de compras.
	 */
	@Override
	public ResponseDTO listarCompras(final Integer pageNo, final Integer pageSize) {
		Page<Compra> comp = compraRepository.findAll(PageRequest.of(pageNo, pageSize));
		return responseService.response(Constantes.SUCCESS_READ, pageResponseService.paginacionDTO(comp,
				comp.getContent().stream().map(compraMapper::compraDTO).toList()));
	}

	/***
	 * Agrega una nueva compra.
	 * 
	 * @param compra datos de la compra.
	 * @return compra registrada.
	 */
	@Override
	public ResponseDTO agregaCompra(final CompraDTO compra) {
		return responseService.response(Constantes.SUCCESS_CREATE,
				compraMapper.compraDTO(compraRepository.save(compraMapper.compraEntity(compra))));
	}

	/***
	 * Metodo que enlista las compras por usuario.
	 * 
	 * @param idUsuario identificador del usuario
	 * @return lista de compras por usuario.
	 */
	@Override
	public ResponseDTO busquedaCompraId(final Integer idUsuario) {
		return responseService.response(Constantes.SUCCESS_READ, compraRepository.findAll().stream()
				.filter(item -> item.getUsuario().getIdUsuario() == idUsuario).map(compraMapper::compraDTO).toList());
	}

}
