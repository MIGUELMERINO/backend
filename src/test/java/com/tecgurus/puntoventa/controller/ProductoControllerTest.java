package com.tecgurus.puntoventa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.google.gson.Gson;

import com.tecgurus.puntoventa.config.ConstantesTest;
import com.tecgurus.puntoventa.service.ProductoService;
import com.tecgurus.puntoventa.dto.CategoriaDTO;
import com.tecgurus.puntoventa.dto.ProductoDTO;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProductoControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Mock
    private ProductoService productoService;

    @Autowired
    private Gson gson;


    private CategoriaDTO categoriaDTO;
    private ProductoDTO productoDTO;

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
    void listaProductos() throws Exception {
        mvc.perform(get(ConstantesTest.API_PRODUCTO)
           .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", ConstantesTest.TOKEN)
        )
            .andDo(print())
            .andExpect(status().isOk());
    }

   	@SuppressWarnings("squid:S2699")
    @Test
    void listaProducto() throws Exception {
        final int id = 1;
        mvc.perform(get(ConstantesTest.API_PRODUCTO + "/" + id)
           .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", ConstantesTest.TOKEN)
        ).andDo(print())
            .andExpect(status().isOk());
    }

   	@SuppressWarnings("squid:S2699")
    @Test
    void agregaProducto() throws Exception {
       mvc.perform(post(ConstantesTest.API_PRODUCTO)
          .content(gson.toJson(productoDTO))
            .header("Authorization", ConstantesTest.TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(status().isOk());
    }

   	@SuppressWarnings("squid:S2699")
    @Test
    void actualizarproducto() throws Exception {
        final int id = 1;
        mvc.perform(put(ConstantesTest.API_PRODUCTO + "/" + id)
           .content(gson.toJson(productoDTO))
           .header("Authorization", ConstantesTest.TOKEN)
           .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
         .andExpect(status().isOk());
    }


   	@SuppressWarnings("squid:S2699")
    @Test
    void busqueda() throws Exception {
        final String producto = "producto test";
        mvc.perform(get(ConstantesTest.API_PRODUCTO + "/busqueda?nombre=" + producto)
           .accept(MediaType.APPLICATION_JSON_VALUE)
           .header("Authorization", ConstantesTest.TOKEN)
        ).andDo(print())
        .andExpect(status().isOk());
    }

}
