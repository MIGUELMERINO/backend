package com.tecgurus.puntoventa.service.serviceImp;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tecgurus.puntoventa.dto.CompraDTO;
import com.tecgurus.puntoventa.entity.Compra;
import com.tecgurus.puntoventa.repository.CompraRepository;
import com.tecgurus.puntoventa.service.CompraService;
import com.tecgurus.puntoventa.utils.Utilidades;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CompraServiceImp implements CompraService{
	
	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private Utilidades utilidades;

	/***
	 * Lista de compras.
	 * @return Lista de compras.
	 */
	@Override
	public List<CompraDTO> listarCompras() {
		return compraRepository.findAll().stream().map(utilidades::compraDTO).collect(Collectors.toList());
	}

	/***
	 * Agrega una nueva compra.
	 * @param compra datos de la compra.
	 * @return compra registrada.
	 */
	@Override
	public CompraDTO agregaCompra(final CompraDTO compra) {
		Compra compras = utilidades.compraEntity(compra);
		return utilidades.compraDTO(compraRepository.save(compras));
	}

	/**
	 * metodo de actualiza la compra.
	 * @param compra datos de la compra.
	 * @param idCompra identificador de la compra.
	 * @return compra actualizada.
	 */
	@Override
	public CompraDTO actualizaCompra(final CompraDTO compra, final Integer idCompra) {
		Compra compras = compraRepository.findById(idCompra)
				.orElseThrow(() -> new EntityNotFoundException("Registro no encontrado"));
		compras.setTotal(compra.getTotal());	
		return utilidades.compraDTO(compraRepository.save(compras));
	}

	/***
	 * Metodo que enlista las compras por usuario.
	 * @param idUsuario identificador del usuario
	 * @return lista de compras por usuario.
	 */
	@Override
	public List<CompraDTO> busquedaCompraId(final Integer idUsuario) {
		return compraRepository.busquedaUsuarioId(idUsuario).stream().map(utilidades::compraDTO).collect(Collectors.toList());
	}

}
