package com.tecgurus.puntoventa.service.serviceimp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

// seccion de importacion de recursos del proyecto
import com.tecgurus.puntoventa.repository.CompraProductoRepository;
import com.tecgurus.puntoventa.service.CompraProductoService;
import com.tecgurus.puntoventa.dto.CompraProductoDTO;
import com.tecgurus.puntoventa.dto.ProductoDTO;
import com.tecgurus.puntoventa.dto.CompraDTO;
import com.tecgurus.puntoventa.dto.UsuarioDTO;
import com.tecgurus.puntoventa.dto.ClienteDTO;
import com.tecgurus.puntoventa.dto.CategoriaDTO;

import java.sql.Date;

class CompraProductoServiceImpTest {

	@MockBean
	private CompraProductoRepository compraProductoRepository;

	@Mock
	private CompraProductoService compraProductoService;

	private CompraProductoDTO compraProductoDTO;
	private ProductoDTO productoDTO;
	private CompraDTO compraDTO;
	private UsuarioDTO usuarioDTO;
	private ClienteDTO clienteDTO;
	private CategoriaDTO categoriaDTO;

	@BeforeEach
	public void init() {

		categoriaDTO = new CategoriaDTO();
		categoriaDTO.setClave(1);
		categoriaDTO.setNombre("Prueba");
		categoriaDTO.setDescripcion("prueba");

		productoDTO = new ProductoDTO();
		productoDTO.setClave(1);
		productoDTO.setDescripcion("descripcion");
		productoDTO.setCategoria(categoriaDTO);
		productoDTO.setPrecio(10.60);
		productoDTO.setImagen("imagen");
		productoDTO.setStock(1);
		productoDTO.setSku("sku");
		productoDTO.setNombre("producto test");

		usuarioDTO = new UsuarioDTO();
		usuarioDTO.setClave(1);
		usuarioDTO.setCorreo("micorreo@gmail.com");
		usuarioDTO.setPassword("tecGurus2024$");
		usuarioDTO.setNombre("Juanito");
		usuarioDTO.setApellidoP("Lopez");
		usuarioDTO.setApellidoM("Lopez");
		usuarioDTO.setEstatus("activo");
		usuarioDTO.setRol("ADMIN");

		clienteDTO = new ClienteDTO();
		clienteDTO.setClave(1);
		clienteDTO.setNombre("Jose");
		clienteDTO.setApellidoP("Perez");
		clienteDTO.setApellidoM("Lion");
		clienteDTO.setRfc("MMdjskd99sda01");

		compraDTO = new CompraDTO();
		compraDTO.setClave(1);
		compraDTO.setFecha(new Date(1));
		compraDTO.setTotal(23.00);
		compraDTO.setCliente(clienteDTO);
		compraDTO.setUsuario(usuarioDTO);

		compraProductoDTO = new CompraProductoDTO();
		compraProductoDTO.setClave(1);
		compraProductoDTO.setCantidad(10);
		compraProductoDTO.setCosto(10.50);
		compraProductoDTO.setCompra(compraDTO);
		compraProductoDTO.setProducto(productoDTO);
		MockitoAnnotations.openMocks(this);
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void listaCompraProductoTest() {
		final Integer page = 0, pageSize = 10;
		compraProductoService.listarComprasProductos(page, pageSize);
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void listoCompraProductoTest() {
		compraProductoService.listarCompraProducto(compraProductoDTO.getClave());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void agregoCompraProductoTest() {
		compraProductoService.agregaCompraProducto(compraProductoDTO);
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void busquecoCompraTest() {
		compraProductoService.busquedaCompra(compraDTO.getClave());
	}

}
