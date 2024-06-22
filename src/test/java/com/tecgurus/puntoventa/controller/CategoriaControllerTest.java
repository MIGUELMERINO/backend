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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.google.gson.Gson;
import com.tecgurus.puntoventa.config.ConstantTest;
import com.tecgurus.puntoventa.dto.CategoriaDTO;
import com.tecgurus.puntoventa.service.CategoriaService;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoriaControllerTest {

	// injectar la dependencia de mokito
	@Autowired
	private MockMvc mvc;

	// crear una instancia para que mokito crear la instancia.
	@Mock
	private CategoriaService categoriaS;

	// injectamos google json para crear el objeto json que se debe de enviar dentro del post y put
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

	@Test
	public void listarCategoriaTest() throws Exception {
		mvc.perform(
				get(ConstantTest.API_CATEGORIA).accept(MediaType.APPLICATION_JSON_VALUE).header("Authorization", ""))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void nuevaCategoriaTest() throws Exception {
		mvc.perform(post(ConstantTest.API_CATEGORIA).content(gson.toJson(categoria)).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void actualizaCategoriaTest() throws Exception {
		categoria.setClave(1);
		mvc.perform(put(ConstantTest.API_CATEGORIA + "/1").content(gson.toJson(categoria)).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk());
	}

}
