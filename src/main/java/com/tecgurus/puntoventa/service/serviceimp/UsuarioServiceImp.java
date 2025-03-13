package com.tecgurus.puntoventa.service.serviceimp;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tecgurus.puntoventa.config.Constantes;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.ResponseDeleteDTO;
import com.tecgurus.puntoventa.dto.UsuarioDTO;
import com.tecgurus.puntoventa.entity.Usuario;
import com.tecgurus.puntoventa.mapper.PasswordEncodeMapper;
import com.tecgurus.puntoventa.mapper.UsuarioMapper;
import com.tecgurus.puntoventa.repository.UsuarioRepository;
import com.tecgurus.puntoventa.service.PageResponseService;
import com.tecgurus.puntoventa.service.ResponseService;
import com.tecgurus.puntoventa.service.UsuarioService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImp implements UsuarioService {

	private UsuarioRepository usuarioR;
	private UsuarioMapper usuarioMapper;
	private ResponseService responseService;
	private PasswordEncodeMapper passworMapper;
	private PageResponseService pageResponseService;

	/***
	 * Lista de todos los usuarios registrados.
	 * 
	 * @param pageNo   numero de pagina.
	 * @param pageSize total de elementos a mostrar.
	 * @return lista de usuarios.
	 */
	@Override
	public ResponseDTO obtenerUsuarios(final Integer pageNo, final Integer pageSize) {
		Page<Usuario> usuario = usuarioR.findAll(PageRequest.of(pageNo, pageSize));
		return responseService.response(Constantes.SUCCESS_READ, 
            pageResponseService.paginacionDTO(usuario, 
usuario.getContent().stream().map(usuarioMapper::usuarioDTO).toList()
            ));
	}

	/**
	 * Metodo que obtiene el usuario mediante su identificador.
	 * 
	 * @param id identificador del usuario.
	 * @return lista de usuario.
	 **/
	@Override
	public ResponseDTO usuario(final Integer id) {
		return responseService.response(Constantes.SUCCESS_READ,
				usuarioR.findById(id).stream().map(usuarioMapper::usuarioDTO).toList());
	}

	/**
	 * Lista de usuarios con el estatus en 1 (activo).
	 *
	 * @return lista de usuarios.
	 */
	@Override
	public ResponseDTO obtenerUsuariosActivos() {
		final int estatus = 1;
		return responseService.response(Constantes.SUCCESS_READ,
				usuarioR.findByActivo(estatus).stream().map(usuarioMapper::usuarioDTO).toList());
	}

	/**
	 * Creacion de usuarios nuevos.
	 * 
	 * @param usuario datos del usuario.
	 * @return retorna un usuario nuevo.
	 */
	@Override
	public ResponseDTO agregaUsuario(final UsuarioDTO usuario) {
		return responseService.response(Constantes.SUCCESS_CREATE,
				usuarioMapper.usuarioDTO(usuarioR.save(usuarioMapper.usuarioEntity(usuario))));
	}

	/**
	 * actualiza un usuario existente.
	 * 
	 * @param usuario   datos del usuario.
	 * @param idUsuario identificador de un usuario.
	 * @return retorna datos del usuario actualizado.
	 */
	@Override
	public ResponseDTO actualizaUsuario(final UsuarioDTO usuario, final Integer idUsuario) {
		Usuario user = usuarioR.findById(idUsuario).orElseThrow(() -> new EntityNotFoundException(Constantes.ERROR));
		user.setEmail(usuario.getCorreo());
		user.setActivo(usuarioMapper.usuarioEntity(usuario).getActivo());
		if (usuario.getPassword() != null) {
			user.setPassword(passworMapper.passwordEncoder(usuario.getPassword()));
		}
		return responseService.response(Constantes.SUCCESS_UPDATE, usuarioR.save(user));
	}

	/**
	 * Elimina un usuario por borrado logico
	 * 
	 * @param idUsuario identificador de usuario.
	 * @return mensaje ok.
	 * 
	 */
	@Override
	public ResponseDeleteDTO eliminaUsuario(final Integer idUsuario) {
		Usuario user = usuarioR.findById(idUsuario).orElseThrow(() -> new EntityNotFoundException(Constantes.ERROR));
		usuarioR.delete(user);
		return responseService.responseDelete(Constantes.SUCCESS_DELETE);
	}

	/***
	 * Metodo consulta el detalle de un usuario registrado y activo.
	 * 
	 * @return datos de usuario.
	 */
	@Override
	public ResponseDTO infoUsuario(final String correo) {
		return responseService.response(Constantes.SUCCESS_READ,
				usuarioMapper.usuarioDTO(usuarioR.findByEmailPassword(correo)));
	}

}
