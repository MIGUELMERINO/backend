package com.tecgurus.puntoventa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecgurus.puntoventa.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	
	/**
	 * se pueden realizar consulta de JPA mediante atributos de la entidad (Clase)
	 * esto deja como resultado una consulta con una condiconal
	 * ejemplo
	 * findBy -> WHERE 
	 * ACTIVO -> activo = ?1
	 * findBy se transforma en una condicional en este caso en SQL (WHERE).
	 * @param activo valor 1
	 * @return todos los usuarios con estatus 1
	 */
	List<Usuario> findByActivo(Integer activo);
	
	@Query("SELECT user FROM Usuario user where user.email =:email and user.activo = 1")
	Usuario findByEmailPassword(@Param("email") String email);

}
