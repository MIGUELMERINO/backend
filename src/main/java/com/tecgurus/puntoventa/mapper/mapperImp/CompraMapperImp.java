package com.tecgurus.puntoventa.mapper.mapperImp;

import com.tecgurus.puntoventa.mapper.CompraMapper;
import com.tecgurus.puntoventa.dto.CompraDTO;
import com.tecgurus.puntoventa.entity.Compra;
import com.tecgurus.puntoventa.mapper.UsuarioMapper;
import com.tecgurus.puntoventa.mapper.ClienteMapper;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class CompraMapperImp implements CompraMapper {
    
    private UsuarioMapper usuarioMapper;
    private ClienteMapper clienteMapper;

	/***
	 * Metodo que crea DTO - Entitidad.
	 * @param compra datos de la entidad compra
	 * @return un dto.
	 */
    @Override
	public CompraDTO compraDTO(Compra compra) {
		CompraDTO dto = new CompraDTO();
		dto.setClave(compra.getIdCompra());
		dto.setCliente(clienteMapper.clienteDTO(compra.getCliente()));
		dto.setFecha(compra.getFecha());
		dto.setTotal(compra.getTotal());
		dto.setUsuario(usuarioMapper.usuarioDTO(compra.getUsuario()));
		
		return dto;
	}

    /***
	 * Metodo que crear Entity - DTO.
	 * @param compraDTO datos del DTO
	 * @return compra entitidad.
	 */
	public Compra compraEntity(CompraDTO compraDTO) {
		Compra compra = new Compra();
		compra.setIdCompra(compraDTO.getClave());
		compra.setCliente(clienteMapper.clienteEntity(compraDTO.getCliente()));
		compra.setUsuario(usuarioMapper.usuarioEntity(compraDTO.getUsuario()));
		compra.setFecha(compraDTO.getFecha());
		compra.setTotal(compraDTO.getTotal());
		
		return compra;
	}
	


}


