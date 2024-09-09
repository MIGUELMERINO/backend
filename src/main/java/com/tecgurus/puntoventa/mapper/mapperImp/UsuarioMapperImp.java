package com.tecgurus.puntoventa.mapper.mapperImp;

import com.tecgurus.puntoventa.mapper.UsuarioMapper;
import com.tecgurus.puntoventa.mapper.PasswordEncodeMapper;
import org.springframework.stereotype.Service;

import com.tecgurus.puntoventa.dto.UsuarioDTO;
import com.tecgurus.puntoventa.entity.Usuario;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor 
public class UsuarioMapperImp implements UsuarioMapper {
   

    private PasswordEncodeMapper passwordEncoder;

     /**
	 * Metodo que convierte una entidad en DTO.
	 * @param usuario datos de usuario.
	 * @return retorna un dto
	 */
    @Override
    public UsuarioDTO usuarioDTO(final Usuario usuario) {
    	UsuarioDTO dto = new UsuarioDTO();
		dto.setClave(usuario.getIdUsuario());
		dto.setCorreo(usuario.getEmail());
		dto.setPassword("");
		dto.setNombre(usuario.getNombre());
		dto.setApellidoP(usuario.getApaterno());
		dto.setApellidoM(usuario.getAmaterno());
		dto.setEstatus(usuario.getActivo() == 1 ? "activo"
						: usuario.getActivo() == 2 ? "cancelado" : "inactivo"); //  operador ternario.
		dto.setRol(usuario.getPerfil());
		
		return dto;
    }


	/***
	 * Metodo que genera de DTO - Entidad.
	 * @param dto datos del DTO.
	 * @return retorna una entidad
	 */
    @Override
    public Usuario usuarioEntity(UsuarioDTO usuarioDTO) {
    	Usuario usuario = new Usuario();
		usuario.setIdUsuario(usuarioDTO.getClave());
		usuario.setEmail(usuarioDTO.getCorreo());
	    usuario.setPassword(passwordEncoder.passwordEncoder(usuarioDTO.getPassword()));
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setApaterno(usuarioDTO.getApellidoP());
		usuario.setAmaterno(usuarioDTO.getApellidoM());
		usuario.setActivo(usuarioDTO.getEstatus().compareToIgnoreCase("activo") == 0 ? 1 
						: usuarioDTO.getEstatus().compareToIgnoreCase("cancelado") == 0 ? 2 : 0
				);
		usuario.setPerfil(usuarioDTO.getRol());
		
		return usuario;

    }

}




