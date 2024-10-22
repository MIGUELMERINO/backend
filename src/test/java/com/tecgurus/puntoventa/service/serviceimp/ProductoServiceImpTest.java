package com.tecgurus.puntoventa.service.serviceimp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

// seccion de importacion de recursos del proyecto
import com.tecgurus.puntoventa.repository.ProductoRepository;
import com.tecgurus.puntoventa.service.ProductoService;
import com.tecgurus.puntoventa.dto.CategoriaDTO;
import com.tecgurus.puntoventa.dto.ProductoDTO;


public class ProductoServiceImpTest {
    
    @MockBean
    private ProductoRepository productoRepository;
    
    @Mock
    private ProductoService productoService;
    
    private ProductoDTO productoDTO;
    private CategoriaDTO categoriaDTO;

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

    @Test
    public void listarProductos() {
        productoService.listarProductos();
    }
    
    @Test
    public void listaProducto() {
        productoService.listaProducto(productoDTO.getClave());
    }

    @Test
    public void agregaProducto() {
        productoService.agregaProducto(productoDTO);
    }

    @Test
    public void actualizaProducto() {
        productoService.actualizaProducto(productoDTO, productoDTO.getClave());
    }

    @Test
    public void busquedaProducto() {
        productoService.busquedaProducto(productoDTO.getNombre());
    }
}
