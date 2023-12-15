package com.tecgurus.puntoventa.service;

import java.util.List;

import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.UsuarioDTO;

public interface UsuarioService {

	/**
	 * Lista de todos los usuarios.
	 * @return lista de usuarios.
	 */
	List<UsuarioDTO> obtenerUsuarios();
	
	/**
	 * Lista de usuarios activos.
	 * @return lista de usuarios.
	 */
	List<UsuarioDTO> obtenerUsuariosActivos();
	
	/**
	 * Crear una usuario nuevo.
	 * @param usuario datos del usuario.
	 * @return un usuario nuevo.
	 */
	UsuarioDTO agregaUsuario(UsuarioDTO usuario);
	
	/**
	 * Actualiza un usuario existente.
	 * @param usuario datos del usuario.
	 * @param idUsuario identificador de usuario.
	 * @return un usuario actualizado.
	 */
	UsuarioDTO actualizaUsuario(UsuarioDTO usuario, Integer idUsuario);
	
	/**
	 * Elimina un usuario
	 * @param idUsuario identificador de usuario.
	 * @return respuesta correcta o error.
	 */
	ResponseDTO eliminaUsuario(Integer idUsuario);
	
	/***
	 * Metodo despliega el detalla de un usuario por su correo.
	 * @param correo email de usuario.
	 * @return datos de usuario registrado y activo.
	 */
	UsuarioDTO infoUsuario(String correo);
	
	
}
