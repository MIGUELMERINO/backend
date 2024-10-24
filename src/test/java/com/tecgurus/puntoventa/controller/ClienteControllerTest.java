package com.tecgurus.puntoventa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
import com.tecgurus.puntoventa.dto.ClienteDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.service.ClienteService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClienteControllerTest {

	@Autowired
	private MockMvc mvc;

	@InjectMocks
	private ClienteController clienteController;

	@Value("${spring.application.secret.key.test}")
	private String token;

	@MockBean
	private ClienteService clienteService;

	@Autowired
	private Gson gson;

	private ClienteDTO cliente;

	@BeforeEach
	public void setup() {
		cliente = new ClienteDTO();
		cliente.setNombre("Jose");
		cliente.setApellidoP("Perez");
		cliente.setApellidoM("Lion");
		cliente.setRfc("MMdjskd99sda01");
		MockitoAnnotations.openMocks(this);
	}

	@SuppressWarnings("squid:S2699")
	// test del controlador
	@Test
	void agregaClienteTest() throws Exception {
		mvc.perform(post(ConstantesTest.API_CLIENTE).content(gson.toJson(cliente)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void listarClientesTest() throws Exception {
		// vamos a realizar un test a los servicios.
		ResponseDTO lista = new ResponseDTO();
		Mockito.when(clienteService.obtenerClientes()).thenReturn(lista);

		mvc.perform(
				get(ConstantesTest.API_CLIENTE).accept(MediaType.APPLICATION_JSON_VALUE).header("Authorization", token))
				.andDo(print()).andExpect(status().isOk());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void actualizaClienteTest() throws Exception {
		cliente.setClave(1);
		mvc.perform(put(ConstantesTest.API_CLIENTE + "/1").content(gson.toJson(cliente)).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk());
	}

}
