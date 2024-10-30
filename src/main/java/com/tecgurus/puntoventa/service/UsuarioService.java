package com.tecgurus.puntoventa.service;

import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.ResponseDeleteDTO;
import com.tecgurus.puntoventa.dto.UsuarioDTO;

public interface UsuarioService {

	/**
	 * Lista de todos los usuarios.
	 * 
	 * @param pageNo   numero de pagina.
	 * @param pageSize total de elementos a mostrar.
	 * @return lista de usuarios.
	 */
	ResponseDTO obtenerUsuarios(Integer pageNo, Integer pageSize);

	/**
	 * Lista de usuarios activos.
	 * 
	 * @return lista de usuarios.
	 */
	ResponseDTO obtenerUsuariosActivos();

	/**
	 * Metodo para obtener un usuario por su identificador.
	 * 
	 * @param id identificador del usuario.
	 * @return un Usuario.
	 **/
	ResponseDTO usuario(Integer id);

	/**
	 * Crear una usuario nuevo.
	 * 
	 * @param usuario datos del usuario.
	 * @return un usuario nuevo.
	 */
	ResponseDTO agregaUsuario(UsuarioDTO usuario);

	/**
	 * Actualiza un usuario existente.
	 * 
	 * @param usuario   datos del usuario.
	 * @param idUsuario identificador de usuario.
	 * @return un usuario actualizado.
	 */
	ResponseDTO actualizaUsuario(UsuarioDTO usuario, Integer idUsuario);

	/**
	 * Elimina un usuario
	 * 
	 * @param idUsuario identificador de usuario.
	 * @return respuesta correcta o error.
	 */
	ResponseDeleteDTO eliminaUsuario(Integer idUsuario);

	/***
	 * Metodo despliega el detalla de un usuario por su correo.
	 * 
	 * @param correo email de usuario.
	 * @return datos de usuario registrado y activo.
	 */
	ResponseDTO infoUsuario(String correo);

}
