package com.tecgurus.puntoventa.service.serviceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.UsuarioDTO;
import com.tecgurus.puntoventa.entity.Usuario;
import com.tecgurus.puntoventa.repository.UsuarioRepository;
import com.tecgurus.puntoventa.service.UsuarioService;
import com.tecgurus.puntoventa.mapper.UsuarioMapper;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioServiceImp implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioR;
	@Autowired
	private UsuarioMapper usuarioMapper;

	/***
	 * Lista de todos los usuarios registrados.
	 * @return lista de usuarios.
	 */
	@Override
	public List<UsuarioDTO> obtenerUsuarios() {
		return usuarioR.findAll().stream().map(usuarioMapper::usuarioDTO).collect(Collectors.toList());
	}

	/**
	 * Lista de usuarios con el estatus en 1 (activo).
	 * @return lista de usuarios.
	 */
	@Override
	public List<UsuarioDTO> obtenerUsuariosActivos() {
		final int estatus = 1;
		return usuarioR.findByActivo(estatus).stream().map(usuarioMapper::usuarioDTO).collect(Collectors.toList());
	}

	/**
	 * Creacion de usuarios nuevos.
	 * @param usuario datos del usuario.
	 * @return retorna un usuario nuevo.
	 */
	@Override
	public UsuarioDTO agregaUsuario(final UsuarioDTO usuario) {
		return usuarioMapper.usuarioDTO(usuarioR.save(usuarioMapper.usuarioEntity(usuario)));
	}

	/**
	 * actualiza un usuario existente.
	 * @param usuario datos del usuario.
	 * @param idUsuario identificador de un usuario.
	 * @return retorna datos del usuario actualizado.
	 */
	@Override
	public UsuarioDTO actualizaUsuario(final UsuarioDTO usuario, final Integer idUsuario) {
		Usuario user = usuarioR.findById(idUsuario)
				.orElseThrow(() -> new EntityNotFoundException("Registro no encontrado"));
		if (user != null) {
            usuario.setClave(user.getIdUsuario());
			usuarioR.save(usuarioMapper.usuarioEntity(usuario));
		}
		return usuario;
	}

	/**
	 * Elimina un usuario por borrado logico
	 * @param idUsuario identificador de usuario.
	 * @return mensaje ok.
	 * 
	 */
	@Override
	public ResponseDTO eliminaUsuario(final Integer idUsuario) {
		Usuario user = usuarioR.findById(idUsuario)
				.orElseThrow(() -> new EntityNotFoundException("Registro no encontrado"));
		ResponseDTO dto = new ResponseDTO();
		if (user != null) {
			dto.setClave("200");
			dto.setValor("Registro eliminado con exito!");
			usuarioR.delete(user);
		}
		return dto;
	}

	/***
	 * Metodo consulta el detalle de un usuario registrado y activo.
	 * @return datos de usuario.
	 */
	@Override
	public UsuarioDTO infoUsuario(final String correo) {
		Usuario usario = usuarioR.findByEmailPassword(correo);
		return usuarioMapper.usuarioDTO(usario);
	}

}
