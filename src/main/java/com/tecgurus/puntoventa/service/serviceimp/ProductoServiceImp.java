package com.tecgurus.puntoventa.service.serviceimp;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tecgurus.puntoventa.config.Constantes;
import com.tecgurus.puntoventa.dto.ProductoDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.ResponseDeleteDTO;
import com.tecgurus.puntoventa.entity.Producto;
import com.tecgurus.puntoventa.repository.ProductoRepository;
import com.tecgurus.puntoventa.service.ProductoService;
import com.tecgurus.puntoventa.service.ResponseService;
import com.tecgurus.puntoventa.mapper.ProductoMapper;
import com.tecgurus.puntoventa.mapper.CategoriaMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoServiceImp implements ProductoService {

	private ProductoRepository productoR;
	private ProductoMapper productoMapper;
	private CategoriaMapper categoriaMapper;
	private ResponseService responseService;

	/**
	 * lista de productos registrados.
	 * 
	 * @return lista de producto registrados.
	 */
	@Override
	public ResponseDTO listarProductos() {
		List<ProductoDTO> productos = productoR.findAll().stream().map(productoMapper::productoDTO).toList();
		return responseService.response(Constantes.SUCCESS_READ, productos);
	}

	@Override
	public ResponseDTO listaProducto(final Integer id) {
		List<ProductoDTO> producto = productoR.findById(id).stream().map(productoMapper::productoDTO).toList();
		return responseService.response(Constantes.SUCCESS_READ, producto);
	}

	/**
	 * Crear un nuevo producto
	 * 
	 * @param producto datos del producto.
	 * @return retorna un nuevo producto.
	 */
	@Override
	public ResponseDTO agregaProducto(final ProductoDTO producto) {
		ProductoDTO productoDTO = productoMapper.productoDTO(productoR.save(productoMapper.productoEntity(producto)));
		return responseService.response(Constantes.SUCCESS_CREATE, productoDTO);
	}

	/**
	 * actualiza un producto registrado.
	 * 
	 * @param producto   datos del producto
	 * @param idProducto identificador del producto.
	 * @return producto actualizado.
	 * 
	 */
	@Override
	public ResponseDTO actualizaProducto(final ProductoDTO producto, final Integer idProducto) {
		Producto p = productoR.findById(idProducto)
				.orElseThrow(() -> new EntityNotFoundException("Registro no encontrado"));
		p.setNombre(producto.getNombre());
		p.setDescripcion(producto.getDescripcion());
		p.setPrecio(producto.getPrecio());
		p.setCategoria(categoriaMapper.categoriaEntity(producto.getCategoria()));
		return responseService.response(Constantes.SUCCESS_UPDATE, productoR.save(p));
	}

	/***
	 * la elimacion de realiza mediante una consulta SQL (nativa).
	 * 
	 * @param idProducto identificador del producto.
	 * @return eliminacion correcta.
	 */
	@Override
	public ResponseDeleteDTO eliminaProducto(final Integer idProducto) {
		productoR.findById(idProducto);
		productoR.findByElimnaProducto(idProducto);
		return responseService.responseDelete(Constantes.SUCCESS_DELETE);
	}

	/***
	 * Metodo busqueda por caracter de un nombre de producto.
	 * 
	 * @param nombreProducto nombre del producto o caracteres a buscar.
	 * @return lista de producto
	 */
	@Override
	public ResponseDTO busquedaProducto(String nombreProducto) {
		List<ProductoDTO> producto = productoR.busquedaProducto(nombreProducto).stream()
				.map(productoMapper::productoDTO).toList();
		return responseService.response(Constantes.SUCCESS_READ, producto);
	}

}
