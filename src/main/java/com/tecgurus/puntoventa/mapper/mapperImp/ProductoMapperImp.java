package com.tecgurus.puntoventa.mapper.mapperImp;

import com.tecgurus.puntoventa.mapper.ProductoMapper;
import org.springframework.stereotype.Service;
import com.tecgurus.puntoventa.dto.ProductoDTO;
import com.tecgurus.puntoventa.entity.Producto;
import com.tecgurus.puntoventa.mapper.CategoriaMapper;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor 
public class ProductoMapperImp implements ProductoMapper {
    
    private CategoriaMapper categoriaMapper;

    /**
	 * Producto Entity - DTO.
	 * @param entity datos de la entidad
	 * @return un objeto DTO.
	 */
	@Override 
    public ProductoDTO productoDTO(Producto entity) {
		ProductoDTO dto = new ProductoDTO();
		dto.setClave(entity.getIdProducto());
		dto.setNombre(entity.getNombre());
		dto.setDescripcion(entity.getDescripcion());
		dto.setPrecio(entity.getPrecio());
		dto.setStock(entity.getStock());
		dto.setSku(entity.getSku());
		dto.setImagen(entity.getImagen());
		dto.setCategoria(categoriaMapper.categoriaDTO(entity.getCategoria()));
		return dto;
	}
	

	/**
	 * Producto DTO - Entity.
	 * @param dto datos del DTO.
	 * @return un objeto entidad.
	 */
    @Override 
    public Producto productoEntity(ProductoDTO dto) {
		Producto producto = new Producto();
		producto.setIdProducto(dto.getClave());
		producto.setNombre(dto.getNombre());
		producto.setDescripcion(dto.getDescripcion());
		producto.setPrecio(dto.getPrecio());
		producto.setStock(dto.getStock());
		producto.setSku(dto.getSku());
		producto.setImagen(dto.getImagen());
		producto.setCategoria(categoriaMapper.categoriaEntity(dto.getCategoria()));
		
		return producto;
	}


}


