package com.tecgurus.puntoventa.mapper.mapperimp;

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

	private static final String ACTIVO = "activo";
	private static final String INACTIVO = "inactivo";

	/**
	 * Metodo que convierte una entidad en DTO.
	 * 
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
		dto.setEstatus(estatusUsuario(usuario.getActivo()));
		dto.setRol(usuario.getPerfil());

		return dto;
	}

	/***
	 * Metodo que genera de DTO - Entidad.
	 * 
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
		usuario.setActivo(statusUsuario(usuarioDTO.getEstatus()));
		usuario.setPerfil(usuarioDTO.getRol());

		return usuario;

	}

	/**
	 * Metodo para validar el estatus.
	 * 
	 * @param estatus valor del estatus del usuario.
	 * @return un estatus activo o inactivo.
	 **/
	public String estatusUsuario(final Integer estatus) {
		return estatus == 1 ? ACTIVO : INACTIVO;
	}

	/**
	 * Metodo para validar el estaus envia desde el request.
	 * 
	 * @param estatus el estatus activo, cancelado o inactivo.
	 * @return un valor 1 (activo), 2 (cancelado), 0 (inactivo).
	 **/
	public Integer statusUsuario(final String estatus) {
		return estatus.compareToIgnoreCase(ACTIVO) == 0 ? 1 : 0;
	}

}
