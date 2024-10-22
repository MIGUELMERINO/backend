package com.tecgurus.puntoventa.service.serviceimp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

// seccion de importacion de recursos del proyecto
import com.tecgurus.puntoventa.repository.CompraRepository;
import com.tecgurus.puntoventa.service.CompraService;
import com.tecgurus.puntoventa.dto.UsuarioDTO;
import com.tecgurus.puntoventa.dto.CompraDTO;
import com.tecgurus.puntoventa.dto.ClienteDTO;

import java.sql.Date;



public class CompraServiceImpTest {
   
    @MockBean
    private CompraRepository compraRepository;

    @Mock
    private CompraService compraService;

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
    public void listarCompras() {
        compraService.listarCompras();
    }

    @Test
    public void agregaCompra() {
        compraService.agregaCompra(compraDTO);
    }

    @Test
    public void busquedaCompraId() {
        compraService.busquedaCompraId(usuarioDTO.getClave());
    }
}


