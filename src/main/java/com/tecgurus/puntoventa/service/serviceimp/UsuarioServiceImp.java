package com.tecgurus.puntoventa.service.serviceimp;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tecgurus.puntoventa.config.Constantes;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.ResponseDeleteDTO;
import com.tecgurus.puntoventa.dto.UsuarioDTO;
import com.tecgurus.puntoventa.entity.Usuario;
import com.tecgurus.puntoventa.mapper.PasswordEncodeMapper;
import com.tecgurus.puntoventa.mapper.UsuarioMapper;
import com.tecgurus.puntoventa.repository.UsuarioRepository;
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

	/***
	 * Lista de todos los usuarios registrados.
	 * @return lista de usuarios.
	 */
	@Override
	public ResponseDTO obtenerUsuarios() {
		List<UsuarioDTO> usuarios =  usuarioR.findAll().stream().map(usuarioMapper::usuarioDTO).toList();
        return responseService.response(Constantes.SUCCESS_READ, usuarios);
	}

    /**
     * Metodo que obtiene el usuario mediante su identificador.
     * @param id identificador del usuario.
     * @return lista de usuario.
     * **/
    @Override
    public ResponseDTO usuario(final Integer id) {
       List<UsuarioDTO> usuario = usuarioR.findById(id).stream().map(usuarioMapper::usuarioDTO).toList();
        return responseService.response(Constantes.SUCCESS_READ, usuario);
    }

	/**
	 * Lista de usuarios con el estatus en 1 (activo).
	 * @return lista de usuarios.
	 */
	@Override
	public ResponseDTO obtenerUsuariosActivos() {
		final int estatus = 1;
		List<UsuarioDTO> usuarios = usuarioR.findByActivo(estatus).stream().map(usuarioMapper::usuarioDTO).toList();
        return responseService.response(Constantes.SUCCESS_READ, usuarios);
	}

	/**
	 * Creacion de usuarios nuevos.
	 * @param usuario datos del usuario.
	 * @return retorna un usuario nuevo.
	 */
	@Override
	public ResponseDTO agregaUsuario(final UsuarioDTO usuario) {
		UsuarioDTO usuarioDTO = usuarioMapper.usuarioDTO(usuarioR.save(usuarioMapper.usuarioEntity(usuario)));
        return responseService.response(Constantes.SUCCESS_CREATE, usuarioDTO);
	}

	/**
	 * actualiza un usuario existente.
	 * @param usuario datos del usuario.
	 * @param idUsuario identificador de un usuario.
	 * @return retorna datos del usuario actualizado.
	 */
	@Override
	public ResponseDTO actualizaUsuario(final UsuarioDTO usuario, final Integer idUsuario) {
		Usuario user = usuarioR.findById(idUsuario)
				.orElseThrow(() -> new EntityNotFoundException(Constantes.ERROR));
	    user.setEmail(usuario.getCorreo());
        user.setActivo(usuarioMapper.usuarioEntity(usuario).getActivo());
        if (usuario.getPassword() != null) {
            user.setPassword(passworMapper.passwordEncoder(usuario.getPassword()));
        }
		return responseService.response(Constantes.SUCCESS_UPDATE,
        usuarioR.save(user));
	}

	/**
	 * Elimina un usuario por borrado logico
	 * @param idUsuario identificador de usuario.
	 * @return mensaje ok.
	 * 
	 */
	@Override
	public ResponseDeleteDTO eliminaUsuario(final Integer idUsuario) {
		Usuario user = usuarioR.findById(idUsuario)
				.orElseThrow(() -> new EntityNotFoundException(Constantes.ERROR));
    	usuarioR.delete(user);
        return responseService.responseDelete(Constantes.SUCCESS_DELETE);
	}

	/***
	 * Metodo consulta el detalle de un usuario registrado y activo.
	 * @return datos de usuario.
	 */
	@Override
	public ResponseDTO infoUsuario(final String correo) {
		Usuario usario = usuarioR.findByEmailPassword(correo);
		return responseService.response(Constantes.SUCCESS_READ, 
            usuarioMapper.usuarioDTO(usario));
	}

}
