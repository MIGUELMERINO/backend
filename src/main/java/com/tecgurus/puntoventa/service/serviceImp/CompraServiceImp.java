package com.tecgurus.puntoventa.service.serviceImp;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tecgurus.puntoventa.dto.CompraDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.repository.CompraRepository;
import com.tecgurus.puntoventa.service.CompraService;
import com.tecgurus.puntoventa.service.ResponseService;
import com.tecgurus.puntoventa.config.Constantes;
import com.tecgurus.puntoventa.mapper.CompraMapper;


@Service
public class CompraServiceImp implements CompraService{
	
	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private CompraMapper compraMapper;
    @Autowired
    private ResponseService responseService;

	/***
	 * Lista de compras.
	 * @return Lista de compras.
	 */
	@Override
	public ResponseDTO listarCompras() {
		List<CompraDTO> compras =  compraRepository.findAll().stream().map(compraMapper::compraDTO).collect(Collectors.toList());
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
		List<CompraDTO> compra =  compraRepository.busquedaUsuarioId(idUsuario).stream().map(compraMapper::compraDTO).collect(Collectors.toList());
        return responseService.response(Constantes.SUCCESS_READ, compra);
	}

}
