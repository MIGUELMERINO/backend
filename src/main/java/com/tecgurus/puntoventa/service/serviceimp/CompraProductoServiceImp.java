package com.tecgurus.puntoventa.service.serviceimp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.tecgurus.puntoventa.config.Constantes;
import com.tecgurus.puntoventa.dto.CompraProductoDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.entity.CompraProducto;
import com.tecgurus.puntoventa.mapper.CompraProductoMapper;
import com.tecgurus.puntoventa.repository.CompraProductoRepository;
import com.tecgurus.puntoventa.service.CompraProductoService;
import com.tecgurus.puntoventa.service.PageResponseService;
import com.tecgurus.puntoventa.service.ResponseService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompraProductoServiceImp implements CompraProductoService {

	private CompraProductoRepository compraPRepository;
	private CompraProductoMapper compraProductoM;
	private ResponseService responseService;
	private PageResponseService pageResponseService;

	/**
	 * Lista de compras con su producto.
	 * 
	 * @param pageNo   numero de pagina.
	 * @param pageSize total de elementos a mostrar.
	 * @return lista de compras con sus producto.
	 */
	@Override
	public ResponseDTO listarComprasProductos(final Integer pageNo, final Integer pageSize) {
		Page<CompraProducto> comp = compraPRepository.findAll(PageRequest.of(pageNo, pageSize));
		return responseService.response(Constantes.SUCCESS_READ, pageResponseService.paginacionDTO(comp,
				comp.getContent().stream().map(compraProductoM::compraProductoDTO).toList()));
	}

	/**
	 * Obtener una lista de Compra Producto mediante su identificador.
	 * 
	 * @param id identificador de la compra producto.
	 * @return lista de una Compra Producto.
	 **/
	@Override
	public ResponseDTO listarCompraProducto(final Integer id) {
		return responseService.response(Constantes.SUCCESS_READ,
				compraPRepository.findById(id).stream().map(compraProductoM::compraProductoDTO).toList());
	}

	/**
	 * Metodo que ingresa una registro de una compra con su producto.
	 * 
	 * @param compraProducto datos de la compra con su producto.
	 * @return una compra exitosa.
	 */
	@Override
	public ResponseDTO agregaCompraProducto(final CompraProductoDTO compraProducto) {
		return responseService.response(Constantes.SUCCESS_CREATE, compraProductoM
				.compraProductoDTO(compraPRepository.save(compraProductoM.compraProductoEntity(compraProducto))));
	}

	/***
	 * Metodo que busca una compra producto por el identificador de la compra.
	 * 
	 * @param idCompra identificador de la compra.
	 * @return detalle de la compra por el idCompra.
	 */
	@Override
	public ResponseDTO busquedaCompra(final Integer idCompra) {
		return responseService.response(Constantes.SUCCESS_READ,
				compraPRepository.findAll().stream().filter(item -> item.getCompra().getIdCompra() == idCompra)
						.map(compraProductoM::compraProductoDTO).toList());
	}

}
