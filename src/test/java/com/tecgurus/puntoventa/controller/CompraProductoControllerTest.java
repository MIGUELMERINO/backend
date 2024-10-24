package com.tecgurus.puntoventa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.tecgurus.puntoventa.config.ConstantesTest;
import com.tecgurus.puntoventa.dto.CategoriaDTO;
import com.tecgurus.puntoventa.dto.ClienteDTO;
import com.tecgurus.puntoventa.dto.CompraDTO;
import com.tecgurus.puntoventa.dto.CompraProductoDTO;
import com.tecgurus.puntoventa.dto.ProductoDTO;
import com.tecgurus.puntoventa.dto.UsuarioDTO;
import com.tecgurus.puntoventa.service.CompraProductoService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CompraProductoControllerTest {

	@Autowired
	private MockMvc mvc;

	@Mock
	private CompraProductoService compraProductoService;

	@Value("${spring.application.secret.key.test}")
	private String TOKEN;

	@Autowired
	private Gson gson;

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
	void listarComprasProducto() throws Exception {
		mvc.perform(get(ConstantesTest.API_COMPRA_PRODUCTO).accept(MediaType.APPLICATION_JSON_VALUE)
				.header("Authorization", TOKEN)).andDo(print()).andExpect(status().isOk());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void agregaCompraProducto() throws Exception {
		mvc.perform(post(ConstantesTest.API_COMPRA_PRODUCTO).content(gson.toJson(compraProductoDTO))
				.header("Authorization", TOKEN).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void compraProductoId() throws Exception {
		final int id = 1;
		mvc.perform(get(ConstantesTest.API_COMPRA_PRODUCTO + "/" + id).accept(MediaType.APPLICATION_JSON_VALUE)
				.header("Authorization", TOKEN)).andDo(print()).andExpect(status().isOk());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void busquedaCompra() throws Exception {
		final int id = 1;
		mvc.perform(get(ConstantesTest.API_COMPRA_PRODUCTO + "/compra/" + id).accept(MediaType.APPLICATION_JSON_VALUE)
				.header("Authorization", TOKEN)).andDo(print()).andExpect(status().isOk());
	}

}
