package com.tecgurus.puntoventa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.tecgurus.puntoventa.config.ConstantTest;
import com.tecgurus.puntoventa.dto.ClienteDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.service.ClienteService;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClienteControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@InjectMocks
	private ClienteController clienteController;
	
	@MockBean
	private ClienteService clienteService;
	
	@Autowired
	private Gson gson;
	
	private ClienteDTO cliente;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setup() {
		cliente = new ClienteDTO();
		cliente.setNombre("Jose");
		cliente.setApellidoP("Perez");
		cliente.setApellidoM("Lion");
		cliente.setRfc("MMdjskd99sda01");
		MockitoAnnotations.initMocks(this);
		this.mvc = MockMvcBuilders.standaloneSetup(clienteController).build();
	}
	
	// test del controlador
	@Test
	public void agregaClienteTest() throws Exception {
		mvc.perform(post(ConstantTest.API_CLIENTE)
				.content(gson.toJson(cliente))
				.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	
	@Test
	public void listarClientesTest() throws Exception {
		// vamos a realizar un test a los servicios.
		ResponseDTO lista = new ResponseDTO();
		Mockito.when(clienteService.obtenerClientes()).thenReturn(lista);
		
		mvc.perform(get(ConstantTest.API_CLIENTE)
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void actualizaClienteTest() throws Exception {
		cliente.setClave(1);
		mvc.perform(put(ConstantTest.API_CLIENTE + "/1")
				.content(gson.toJson(cliente))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isOk());
	}
	

}
