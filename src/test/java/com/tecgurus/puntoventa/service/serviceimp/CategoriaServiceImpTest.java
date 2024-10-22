package com.tecgurus.puntoventa.service.serviceimp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

// seccion de importacion de recursos del proyecto
import com.tecgurus.puntoventa.repository.CategoriaRepository;
import com.tecgurus.puntoventa.service.CategoriaService;
import com.tecgurus.puntoventa.dto.CategoriaDTO;



public class CategoriaServiceImpTest {
   
    @MockBean
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaService categoriaService;

    private CategoriaDTO categoria;	

    @BeforeEach
	public void setup() {
		categoria = new CategoriaDTO();
        categoria.setClave(1);
		categoria.setNombre("Prueba");
		categoria.setDescripcion("prueba");
		MockitoAnnotations.openMocks(this);
	}


    @Test
    public void listaCategorias() {
        categoriaService.listaCategorias();
    }

    @Test
    public void listaCategoriaId() {
        categoriaService.listaCategoriaId(categoria.getClave());
    }

    @Test
    public void agregaCategoria() {
        categoriaService.agregaCategoria(categoria);
    }

    @Test
    public void actualizarCategoria() {
        categoriaService.actualizarCategoria(categoria, categoria.getClave());
    }

    @Test
    public void busquedaCategoria() {
        categoriaService.busquedaCategoria(categoria.getNombre());
    }

}
