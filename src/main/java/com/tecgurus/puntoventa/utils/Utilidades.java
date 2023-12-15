package com.tecgurus.puntoventa.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tecgurus.puntoventa.dto.CategoriaDTO;
import com.tecgurus.puntoventa.dto.ClienteDTO;
import com.tecgurus.puntoventa.dto.CompraDTO;
import com.tecgurus.puntoventa.dto.CompraProductoDTO;
import com.tecgurus.puntoventa.dto.ProductoDTO;
import com.tecgurus.puntoventa.dto.UsuarioDTO;
import com.tecgurus.puntoventa.entity.Categoria;
import com.tecgurus.puntoventa.entity.Cliente;
import com.tecgurus.puntoventa.entity.Compra;
import com.tecgurus.puntoventa.entity.CompraProducto;
import com.tecgurus.puntoventa.entity.Producto;
import com.tecgurus.puntoventa.entity.Usuario;

@Component //  el componente es parte de core de spring boot es algo similar bean Autowired
public class Utilidades {

	
	@Autowired
	private PasswordEncoder passwordEncoder;

	/***
	 * Metodo que convierte de entidad - dto.
	 * @param entidad datos 
	 * @return un dto.
	 */
	public CategoriaDTO categoriaDTO(Categoria entidad) {
		CategoriaDTO dto = new CategoriaDTO();
		dto.setClave(entidad.getIdCategoria());
		dto.setNombre(entidad.getNombre());
		dto.setDescripcion(entidad.getDescripcion());
		return dto;
	}
	
	/***
	 * Metodo que convierte de DTO - Etidad.
	 * @param dto datos enviados.
	 * @return entindad.
	 */
	public Categoria categoriaEntity(CategoriaDTO dto) {
		Categoria entidad = new Categoria();
		entidad.setIdCategoria(dto.getClave()); // null -> nuevo, si no es null hay que actualizar.
		entidad.setNombre(dto.getNombre());
		entidad.setDescripcion(dto.getDescripcion());
		return entidad;
	}
	
	
	/***
	 * Metodo que convierte de Entidad a DTO.
	 * @param cliente datos del cliente.
	 * @return un dto
	 */
	public ClienteDTO clienteDTO(Cliente cliente) {
		ClienteDTO dto = new ClienteDTO();
		dto.setClave(cliente.getIdCliente());
		dto.setNombre(cliente.getNombre());
		dto.setApellidoP(cliente.getApaterno());
		dto.setApellidoM(cliente.getAmaterno());
		dto.setRfc(cliente.getRfc());
		
		return dto;
	}
	
	/***
	 * Metodo que convierte DTO a Entidad.
	 * @param dto datos del DTO
	 * @return retorna una entidad.
	 */
	public Cliente clienteEntity(ClienteDTO dto) {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(dto.getClave());
		cliente.setNombre(dto.getNombre());
		cliente.setApaterno(dto.getApellidoP());
		cliente.setAmaterno(dto.getApellidoM());
		cliente.setRfc(dto.getRfc());
		return cliente;
	}
	
	
	/**
	 * Metodo que convierte una entidad en DTO.
	 * @param usuario datos de usuario.
	 * @return retorna un dto
	 */
	public UsuarioDTO usuarioDTO(Usuario usuario) {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setClave(usuario.getIdUsuario());
		dto.setCorreo(usuario.getEmail());
		dto.setPassword("");
		dto.setNombre(usuario.getNombre());
		dto.setApellidoP(usuario.getApaterno());
		dto.setApellidoM(usuario.getAmaterno());
		// if (usuario.getActivo == 0) { dto.setEstatus("inactivo") } else { dto.setEstatus("activo") } 
		dto.setEstatus(
				usuario.getActivo() == 1 ? "activo"
						: usuario.getActivo() == 2 ? "cancelado" : "inactivo"
				); //  operador ternario.
		dto.setRol(usuario.getPerfil());
		
		return dto;
	}
	
	/***
	 * Metodo que genera de DTO - Entidad.
	 * @param dto datos del DTO.
	 * @return retorna una entidad
	 */
	public Usuario usuarioEntity(UsuarioDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(dto.getClave());
		usuario.setEmail(dto.getCorreo());
		usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
		usuario.setNombre(dto.getNombre());
		usuario.setApaterno(dto.getApellidoP());
		usuario.setAmaterno(dto.getApellidoM());
		usuario.setActivo(
				dto.getEstatus().compareToIgnoreCase("activo") == 0 ? 1 
						: dto.getEstatus().compareToIgnoreCase("cancelado") == 0 ? 2 : 0
				);
		usuario.setPerfil(dto.getRol());
		
		return usuario;
	}
	
	
	/**
	 * Producto Entity - DTO.
	 * @param entity datos de la entidad
	 * @return un objeto DTO.
	 */
	public ProductoDTO productoDTO(Producto entity) {
		ProductoDTO dto = new ProductoDTO();
		dto.setClave(entity.getIdProducto());
		dto.setNombre(entity.getNombre());
		dto.setDescripcion(entity.getDescripcion());
		dto.setPrecio(entity.getPrecio());
		dto.setStock(entity.getStock());
		dto.setSku(entity.getSku());
		dto.setImagen(entity.getImagen());
		dto.setCategoria(categoriaDTO(entity.getCategoria()));
		
		return dto;
	}
	
	/**
	 * Producto DTO - Entity.
	 * @param dto datos del DTO.
	 * @return un objeto entidad.
	 */
	public Producto productoEntity(ProductoDTO dto) {
		Producto producto = new Producto();
		producto.setIdProducto(dto.getClave());
		producto.setNombre(dto.getNombre());
		producto.setDescripcion(dto.getDescripcion());
		producto.setPrecio(dto.getPrecio());
		producto.setStock(dto.getStock());
		producto.setSku(dto.getSku());
		producto.setImagen(dto.getImagen());
		producto.setCategoria(categoriaEntity(dto.getCategoria()));
		
		return producto;
	}
	
	/***
	 * Metodo que crea DTO - Entitidad.
	 * @param compra datos de la entidad compra
	 * @return un dto.
	 */
	public CompraDTO compraDTO(Compra compra) {
		CompraDTO dto = new CompraDTO();
		dto.setClave(compra.getIdCompra());
		dto.setCliente(clienteDTO(compra.getCliente()));
		dto.setFecha(compra.getFecha());
		dto.setTotal(compra.getTotal());
		dto.setUsuario(usuarioDTO(compra.getUsuario()));
		
		return dto;
	}
	
	/***
	 * Metodo que crear Entity - DTO.
	 * @param compraDTO datos del DTO
	 * @return compra entitidad.
	 */
	public Compra compraEntity(CompraDTO compraDTO) {
		Compra compra = new Compra();
		compra.setIdCompra(compraDTO.getClave());
		compra.setCliente(clienteEntity(compraDTO.getCliente()));
		compra.setUsuario(usuarioEntity(compraDTO.getUsuario()));
		compra.setFecha(compraDTO.getFecha());
		compra.setTotal(compraDTO.getTotal());
		
		return compra;
	}
	
	
	/***
	 * Metodo Compra Producto DTO - entity.
	 * @param compraProducto datos compra producto
	 * @return dto.
	 */
	public CompraProductoDTO compraProductoDTO(CompraProducto compraProducto) {
		CompraProductoDTO dto = new CompraProductoDTO();
		dto.setClave(compraProducto.getIdCompraProducto());
		dto.setCantidad(compraProducto.getCantidad());
		dto.setCosto(compraProducto.getCosto());
		dto.setCompra(compraDTO(compraProducto.getCompra()));
		dto.setProducto(productoDTO(compraProducto.getProducto()));
		
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
		compraP.setCompra(compraEntity(compraPDTO.getCompra()));
		compraP.setProducto(productoEntity(compraPDTO.getProducto()));
		
		return compraP;
	}
	
	
}
