package com.tecgurus.puntoventa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.tecgurus.puntoventa.service.CategoriaService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CategoriaControllerTest {

	// injectar la dependencia de mokito
	@Autowired
	private MockMvc mvc;

	@Value("${spring.application.secret.key.test}")
	private String TOKEN;

	// crear una instancia para que mokito crear la instancia.
	@Mock
	private CategoriaService categoriaS;

	// injectamos google json para crear el objeto json que se debe de enviar dentro
	// del post y put
	@Autowired
	private Gson gson;

	// crea la variable de la clase DTO a enviar a la api.
	private CategoriaDTO categoria;

	@BeforeEach
	public void setup() {
		categoria = new CategoriaDTO();
		categoria.setNombre("Prueba");
		categoria.setDescripcion("prueba");
		MockitoAnnotations.openMocks(this);
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void listarCategoriaTest() throws Exception {
		mvc.perform(get(ConstantesTest.API_CATEGORIA).accept(MediaType.APPLICATION_JSON_VALUE).header("Authorization",
				TOKEN)).andDo(print()).andExpect(status().isOk());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void nuevaCategoriaTest() throws Exception {
		mvc.perform(post(ConstantesTest.API_CATEGORIA).accept(MediaType.APPLICATION_JSON_VALUE)
				.header("Authorization", TOKEN).content(gson.toJson(categoria))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void actualizaCategoriaTest() throws Exception {
		categoria.setClave(1);
		mvc.perform(put(ConstantesTest.API_CATEGORIA + "/1").accept(MediaType.APPLICATION_JSON_VALUE)
				.header("Authorization", TOKEN).content(gson.toJson(categoria))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk());
	}

}
