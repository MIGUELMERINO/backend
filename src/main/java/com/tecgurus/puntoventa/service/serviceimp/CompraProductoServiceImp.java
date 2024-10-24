package com.tecgurus.puntoventa.service.serviceimp;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tecgurus.puntoventa.config.Constantes;
import com.tecgurus.puntoventa.dto.CompraProductoDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.repository.CompraProductoRepository;
import com.tecgurus.puntoventa.service.CompraProductoService;
import com.tecgurus.puntoventa.service.ResponseService;
import com.tecgurus.puntoventa.mapper.CompraProductoMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompraProductoServiceImp implements CompraProductoService {
	
	private CompraProductoRepository compraPRepository;
	private CompraProductoMapper compraProductoM;
    private ResponseService responseService;

	/**
	 * Lista de compras con su producto.
	 * @return lista de compras con sus producto.
	 */
	@Override
	public ResponseDTO listarComprasProductos() {
		List<CompraProductoDTO> compras =  compraPRepository.findAll().stream().map(compraProductoM::compraProductoDTO).toList();
        return responseService.response(Constantes.SUCCESS_READ, compras);
	}


    @Override
    public ResponseDTO listarCompraProducto(final Integer id) {
        List<CompraProductoDTO> compra = compraPRepository.findById(id).stream().map(compraProductoM::compraProductoDTO).toList();
        return responseService.response(Constantes.SUCCESS_READ, compra);
    }

	/**
	 * Metodo que ingresa una registro de una compra con su producto.
	 * @param compraProducto datos de la compra con su producto.
	 * @return una compra exitosa.
	 */
	@Override
	public ResponseDTO agregaCompraProducto(final CompraProductoDTO compraProducto) {
		CompraProductoDTO compraP = compraProductoM.compraProductoDTO(compraPRepository.save(compraProductoM.compraProductoEntity(compraProducto)));
        return responseService.response(Constantes.SUCCESS_CREATE, compraP);
	}

	/***
	 * Metodo que busca una compra producto por el identificador de la compra.
	 * @param idCompra identificador de la compra.
	 * @return detalle de la compra por el idCompra.
	 */
	@Override
	public ResponseDTO busquedaCompra(final Integer idCompra) {
		List<CompraProductoDTO> producto = compraPRepository.busquedaCompra(idCompra)
				.stream().map(compraProductoM::compraProductoDTO).toList();		
		return responseService.response(Constantes.SUCCESS_READ, producto);
	}

}
