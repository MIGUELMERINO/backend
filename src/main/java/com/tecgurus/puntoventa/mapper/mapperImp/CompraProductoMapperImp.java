package com.tecgurus.puntoventa.mapper.mapperImp;

import com.tecgurus.puntoventa.mapper.CompraProductoMapper;
import com.tecgurus.puntoventa.mapper.ProductoMapper;
import com.tecgurus.puntoventa.mapper.CompraMapper;
import com.tecgurus.puntoventa.dto.CompraProductoDTO;
import com.tecgurus.puntoventa.entity.CompraProducto;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class CompraProductoMapperImp implements CompraProductoMapper {
    
    private CompraMapper compraMapper;
    private ProductoMapper productoMapper;

    /***
	 * Metodo Compra Producto DTO - entity.
	 * @param compraProducto datos compra producto
	 * @return dto.
	 */
	@Override 
    public CompraProductoDTO compraProductoDTO(CompraProducto compraProducto) {
		CompraProductoDTO dto = new CompraProductoDTO();
		dto.setClave(compraProducto.getIdCompraProducto());
		dto.setCantidad(compraProducto.getCantidad());
		dto.setCosto(compraProducto.getCosto());
		dto.setCompra(compraMapper.compraDTO(compraProducto.getCompra()));
		dto.setProducto(productoMapper.productoDTO(compraProducto.getProducto()));
		
		return dto;
	}

	/***
	 * Metodo Compra Producto DTO - Entity
	 * @param compraPDTO datos del DTO
	 * @return returna una entidad.
	 */
	public CompraProducto compraProductoEntity(CompraProductoDTO compraPDTO) {
		CompraProducto compraP = new CompraProducto();
		compraP.setIdCompraProducto(compraPDTO.getClave());
		compraP.setCantidad(compraPDTO.getCantidad());
		compraP.setCosto(compraPDTO.getCosto());
		compraP.setCompra(compraMapper.compraEntity(compraPDTO.getCompra()));
		compraP.setProducto(productoMapper.productoEntity(compraPDTO.getProducto()));
		
		return compraP;
	}
	}


