package com.tecgurus.puntoventa.service.serviceimp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

// seccion de importacion de recursos del proyecto
import com.tecgurus.puntoventa.repository.ProductoRepository;
import com.tecgurus.puntoventa.service.ProductoService;
import com.tecgurus.puntoventa.dto.CategoriaDTO;
import com.tecgurus.puntoventa.dto.ProductoDTO;

class ProductoServiceImpTest {

	@MockBean
	private ProductoRepository productoRepository;

	@Mock
	private ProductoService productoService;

	private ProductoDTO productoDTO;
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

		MockitoAnnotations.openMocks(this);
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void listarProductosTest() {
		productoService.listarProductos();
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void listaProductoTest() {
		productoService.listaProducto(productoDTO.getClave());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void agregaProductoTest() {
		productoService.agregaProducto(productoDTO);
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void actualizaProductoTest() {
		productoService.actualizaProducto(productoDTO, productoDTO.getClave());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void busquedaProductoTest() {
		productoService.busquedaProducto(productoDTO.getNombre());
	}

}
