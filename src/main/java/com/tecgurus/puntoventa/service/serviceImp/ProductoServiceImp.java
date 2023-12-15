package com.tecgurus.puntoventa.service.serviceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecgurus.puntoventa.dto.ProductoDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.entity.Producto;
import com.tecgurus.puntoventa.repository.ProductoRepository;
import com.tecgurus.puntoventa.service.ProductoService;
import com.tecgurus.puntoventa.utils.Utilidades;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoServiceImp implements ProductoService {

	@Autowired
	private ProductoRepository productoR;
	private Utilidades utils;
	
	/**
	 * lista de productos registrados.
	 * @return lista de producto registrados.
	 */
	@Override
	public List<ProductoDTO> listarProductos() {
		return productoR.findAll().stream().map(utils::productoDTO).collect(Collectors.toList());
	}


	/**
	 * Crear un nuevo producto
	 * @param producto datos del producto.
	 * @return retorna un nuevo producto.
	 */
	@Override
	public ProductoDTO agregaProducto(final ProductoDTO producto) {
		Producto p = utils.productoEntity(producto);
		return utils.productoDTO(productoR.save(p));
	}

	/**
	 * actualiza un producto registrado.
	 * @param producto datos del producto
	 * @param idProducto identificador del producto.
	 * @return producto actualizado.
	 * 
	 */
	@Override
	public ProductoDTO actualizaProducto(final ProductoDTO producto, final Integer idProducto) {
		Producto p = productoR.findById(idProducto)
				.orElseThrow(() -> new EntityNotFoundException("Registro no encontrado"));
		if (p != null) {
			p.setNombre(producto.getNombre());
			p.setDescripcion(producto.getDescripcion());
			p.setPrecio(producto.getPrecio());
			p.setCategoria(utils.categoriaEntity(producto.getCategoria()));
			productoR.save(p);
		}
		return producto;
	}

	/***
	 * la elimacion de realiza mediante una consulta SQL (nativa).
	 * @param idProducto identificador del producto.
	 * @return eliminacion correcta.
	 */
	@Override
	public ResponseDTO eliminaProducto(final Integer idProducto) {
		Producto producto = productoR.findById(idProducto)
				.orElseThrow(() -> new EntityNotFoundException("Registro no encontrado"));
		ResponseDTO dto = new ResponseDTO();
		if (producto != null) {
			dto.setClave("200");
			dto.setValor("Registro eliminado");
			productoR.findByElimnaProducto(idProducto);
		}
		return dto;
	}

	/***
	 * Metodo busqueda por caracter de un nombre de producto.
	 * @param nombreProducto nombre del producto o caracteres a buscar.
	 * @return lista de producto
	 */
	@Override
	public List<ProductoDTO> busquedaProducto(String nombreProducto) {
		return productoR.busquedaProducto(nombreProducto).stream().map(utils::productoDTO).collect(Collectors.toList());
	}

}
