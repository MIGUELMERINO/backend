package com.tecgurus.puntoventa.service.serviceimp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tecgurus.puntoventa.dto.CategoriaDTO;
// seccion de importacion de recursos del proyecto
import com.tecgurus.puntoventa.repository.CategoriaRepository;
import com.tecgurus.puntoventa.service.CategoriaService;

class CategoriaServiceImpTest {

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

	@SuppressWarnings("squid:S2699")
	@Test
	void listarCategoriasTest() {
		final Integer pageNo = 0, pageSize = 10;
		categoriaService.listaCategorias(pageNo, pageSize);
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void listoCategoriaIdTest() {
		categoriaService.listaCategoriaId(categoria.getClave());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void agregoCategoriaTest() {
		categoriaService.agregaCategoria(categoria);
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void actualizoCategoriaTest() {
		categoriaService.actualizarCategoria(categoria, categoria.getClave());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void busquecoCategoriaTest() {
		categoriaService.busquedaCategoria(categoria.getNombre());
	}

}
