package com.tecgurus.puntoventa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.google.gson.Gson;
import java.sql.Date;

import com.tecgurus.puntoventa.config.ConstantTest;

// importamos los valores las clases y servicios para realizar test.
import com.tecgurus.puntoventa.service.CompraService;
import com.tecgurus.puntoventa.dto.CompraDTO;
import com.tecgurus.puntoventa.dto.UsuarioDTO;
import com.tecgurus.puntoventa.dto.ClienteDTO;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CompraControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Mock
    private CompraService compraService;


    @Autowired
    private Gson gson;

    private CompraDTO compraDTO;
    private UsuarioDTO usuarioDTO;
    private ClienteDTO clienteDTO;

    @BeforeEach
    public void init() {
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
        MockitoAnnotations.openMocks(this);
   }

    @Test
    public void listaCompras() throws Exception {
        mvc.perform(get(ConstantTest.API_COMPRA)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .header("Authorization", ConstantTest.TOKEN))
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    public void agregarCompra() throws Exception {
       mvc.perform(post(ConstantTest.API_COMPRA)
        .content(gson.toJson(compraDTO))
            .header("Authorization", ConstantTest.TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isOk()); 
    }

    @Test
    public void busquedaCompra() throws Exception {
        final int id = 1;
        mvc.perform(get(ConstantTest.API_COMPRA+"/usuario/"+id)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", ConstantTest.TOKEN)
        )
        .andDo(print())
        .andExpect(status().isOk());        
    }

}  



