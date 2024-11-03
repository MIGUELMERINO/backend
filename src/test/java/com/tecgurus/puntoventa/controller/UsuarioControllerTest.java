package com.tecgurus.puntoventa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.tecgurus.puntoventa.config.ConstantesTest;
import com.tecgurus.puntoventa.dto.UsuarioDTO;
import com.tecgurus.puntoventa.service.UsuarioService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UsuarioControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private Gson gson;

	@Value("${spring.application.secret.test}")
	private String token;

	@MockBean
	private UsuarioService usuarioService;

	private UsuarioDTO dto;

	@BeforeEach
	public void init() {
		dto = new UsuarioDTO();
		dto.setClave(1);
		dto.setCorreo("micorreo@gmail.com");
		dto.setPassword("tecGurus2024$");
		dto.setNombre("Juanito");
		dto.setApellidoP("Lopez");
		dto.setApellidoM("Lopez");
		dto.setEstatus("activo");
		dto.setRol("ADMIN");
		MockitoAnnotations.openMocks(this);

	}

	@SuppressWarnings("squid:S2699")
	@Test
	void listarUsuariosTest() throws Exception {
		this.mvc.perform(
				get(ConstantesTest.API_USUARIO).accept(MediaType.APPLICATION_JSON).header("Authorization", token))
				.andDo(print()).andExpect(status().isOk());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void listaUsuariosActivosTest() throws Exception {
		this.mvc.perform(get(ConstantesTest.API_USUARIO + "/activos").accept(MediaType.APPLICATION_JSON)
				.header("Authorization", token)).andDo(print()).andExpect(status().isOk());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void agregaUsuarioTest() throws Exception {
		this.mvc.perform(post(ConstantesTest.API_USUARIO).contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(this.dto)).header("Authorization", token)).andDo(print())
				.andExpect(status().isOk());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void actualizaUsuarioTest() throws Exception {
		this.mvc.perform(put(ConstantesTest.API_USUARIO + "/1").contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(this.dto)).header("Authorization", token)).andDo(print())
				.andExpect(status().isOk());
	}

}
