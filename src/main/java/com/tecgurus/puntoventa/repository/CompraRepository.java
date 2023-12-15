package com.tecgurus.puntoventa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecgurus.puntoventa.entity.Compra;


public interface CompraRepository extends JpaRepository<Compra, Integer>{
	
	@Query(value = "SELECT * FROM compra where idusuario = :idUsuario ", nativeQuery = true)
	List<Compra> busquedaUsuarioId(@Param("idUsuario") Integer idUsuario);

}
