package com.tecgurus.puntoventa.service.serviceimp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tecgurus.puntoventa.config.Constantes;
import com.tecgurus.puntoventa.dto.CompraDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.mapper.CompraMapper;
import com.tecgurus.puntoventa.repository.CompraRepository;
import com.tecgurus.puntoventa.service.CompraService;
import com.tecgurus.puntoventa.service.ResponseService;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class CompraServiceImp implements CompraService{
	
	private CompraRepository compraRepository;
	private CompraMapper compraMapper;
    private ResponseService responseService;

	/***
	 * Lista de compras.
	 * @return Lista de compras.
	 */
	@Override
	public ResponseDTO listarCompras() {
		List<CompraDTO> compras =  compraRepository.findAll().stream().map(compraMapper::compraDTO).toList();
        return responseService.response(Constantes.SUCCESS_READ, compras);
	}

	/***
	 * Agrega una nueva compra.
	 * @param compra datos de la compra.
	 * @return compra registrada.
	 */
	@Override
	public ResponseDTO agregaCompra(final CompraDTO compra) {
		CompraDTO compraDTO =  compraMapper.compraDTO(compraRepository.save(compraMapper.compraEntity(compra)));
        return responseService.response(Constantes.SUCCESS_CREATE, compraDTO);
	}

	/***
	 * Metodo que enlista las compras por usuario.
	 * @param idUsuario identificador del usuario
	 * @return lista de compras por usuario.
	 */
	@Override
	public ResponseDTO busquedaCompraId(final Integer idUsuario) {
		List<CompraDTO> compra =  compraRepository.busquedaUsuarioId(idUsuario).stream().map(compraMapper::compraDTO).toList();
        return responseService.response(Constantes.SUCCESS_READ, compra);
	}

}
