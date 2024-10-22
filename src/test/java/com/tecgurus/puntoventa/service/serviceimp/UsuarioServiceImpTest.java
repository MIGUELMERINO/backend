package com.tecgurus.puntoventa.service.serviceimp;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.tecgurus.puntoventa.dto.UsuarioDTO;
import com.tecgurus.puntoventa.repository.UsuarioRepository;
import com.tecgurus.puntoventa.service.UsuarioService;


public class UsuarioServiceImpTest {
	
	
	@MockBean
	private UsuarioRepository usuarioRepository;
	@Mock
	private UsuarioService usuarioService;
	
	/**
	 * creamos el objeto de la clase DTO global y que se ocupara el mismo
	 * dato en crear, actualizar
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
	
	@Test
	public void agregaUsuarioTest() {
		usuarioService.agregaUsuario(dto);
	}
	
	@Test
	public void actualizaUsuario() {
		usuarioService.actualizaUsuario(dto, dto.getClave());
	}

	@Test
	public void obtenerUsuariosTest() {
		usuarioService.obtenerUsuarios();
	}
	
	@Test
	public void obtenerUsuariosActivos() {
		usuarioService.obtenerUsuariosActivos();
	}
	
	@Test
	public void eliminaUsuarioTest() {
		usuarioService.eliminaUsuario(dto.getClave());
	}
	
	@Test
	public void infoUsuarioTest() {
		usuarioService.infoUsuario(dto.getCorreo());
	}
	
}
