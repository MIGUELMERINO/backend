package com.tecgurus.puntoventa.service.serviceimp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.tecgurus.puntoventa.dto.UsuarioDTO;
import com.tecgurus.puntoventa.repository.UsuarioRepository;
import com.tecgurus.puntoventa.service.UsuarioService;

class UsuarioServiceImpTest {

	@MockBean
	private UsuarioRepository usuarioRepository;
	@Mock
	private UsuarioService usuarioService;

	/**
	 * creamos el objeto de la clase DTO global y que se ocupara el mismo dato en
	 * crear, actualizar
	 */
	private UsuarioDTO dto;

	@BeforeEach
	public void init() {
		dto = new UsuarioDTO();
		dto.setClave(1);
		dto.setCorreo("micorreogmail.com");
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
	void agregaUsuarioTest() {
		usuarioService.agregaUsuario(dto);
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void actualizaUsuarioTest() {
		usuarioService.actualizaUsuario(dto, dto.getClave());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void obtenerUsuariosTest() {
		usuarioService.obtenerUsuarios();
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void obtenerUsuariosActivosTest() {
		usuarioService.obtenerUsuariosActivos();
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void eliminaUsuarioTest() {
		usuarioService.eliminaUsuario(dto.getClave());
	}

	@SuppressWarnings("squid:S2699")
	@Test
	void infoUsuarioTest() {
		usuarioService.infoUsuario(dto.getCorreo());
	}

}
