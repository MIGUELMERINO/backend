package com.tecgurus.puntoventa.service.serviceImp;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tecgurus.puntoventa.dto.CompraProductoDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.entity.CompraProducto;
import com.tecgurus.puntoventa.repository.CompraProductoRepository;
import com.tecgurus.puntoventa.service.CompraProductoService;
import com.tecgurus.puntoventa.utils.Utilidades;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompraProductoServiceImp implements CompraProductoService {
	
	private CompraProductoRepository compraPRepository;
	private Utilidades utilidades;

	/**
	 * Lista de compras con su producto.
	 * @return lista de compras con sus producto.
	 */
	@Override
	public List<CompraProductoDTO> listarComprasProductos() {
		return compraPRepository.findAll().stream().map(utilidades::compraProductoDTO).collect(Collectors.toList());
	}

	/**
	 * Metodo que ingresa una registro de una compra con su producto.
	 * @param compraProducto datos de la compra con su producto.
	 * @return una compra exitosa.
	 */
	@Override
	public CompraProductoDTO agregaCompraProducto(final CompraProductoDTO compraProducto) {
		CompraProducto compra = utilidades.compraProductoEntity(compraProducto);
		return utilidades.compraProductoDTO(compraPRepository.save(compra));
	}

	/**
	 * Metodo que actualiza la cantidad y el costo de una compra con su producto
	 * @param idCompraProducto identificador de la compra con su producto.
	 * @param compraProducto datos de la compra a actualizar.
	 * @return compraProducto actualizada.
	 */
	@Override
	public CompraProductoDTO actualizarCompraProducto(final Integer idCompraProducto, final CompraProductoDTO compraProducto) {
		CompraProducto compra = compraPRepository.findById(idCompraProducto)
				.orElseThrow(() -> new EntityNotFoundException("El registro no se encuentra"));
		
		compra.setCantidad(compraProducto.getCantidad());
		compra.setCosto(compraProducto.getCosto());
		
		return utilidades.compraProductoDTO(compraPRepository.save(compra));
	}

	/***
	 * Metodo que busca una compra producto por el identificador de la compra.
	 * @param idCompra identificador de la compra.
	 * @return detalle de la compra por el idCompra.
	 */
	@Override
	public ResponseDTO busquedaCompra(final Integer idCompra) {
		List<CompraProductoDTO> producto = compraPRepository.busquedaCompra(idCompra)
				.stream().map(utilidades::compraProductoDTO).collect(Collectors.toList());
		ResponseDTO response = new ResponseDTO();
		// Objects.nonNull sirve para validad que el objeto no sea nulo.
		if (Objects.nonNull(producto)) {
			response.setClave("200");
			response.setValor("exitoso");
			response.setPlayLoad(producto);
		} else {
			response.setClave("600");
			response.setValor("Datos no encontrados");
		}
		
		return response;
	}

}
