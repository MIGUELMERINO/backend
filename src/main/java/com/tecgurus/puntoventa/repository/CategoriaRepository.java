package com.tecgurus.puntoventa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecgurus.puntoventa.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	// @Query tiene dos opciones
	// consulta por objeto
	// consulta con un lenguaje SQL.

	// consulta por objeto.
	@Query("SELECT c FROM Categoria c WHERE c.idCategoria = :idCategoria")
	// internamente es select * from categoria c where idcategoria = 1
	Categoria busquedaPorId(@Param("idCategoria") Integer idCategoria);

	// consulta nativa.
	@Query(value = "SELECT * FROM categoria WHERE idcategoria = ?1", nativeQuery = true)
	// internamente es select * from categoria c where idcategoria = 1
	Categoria findByIds(Integer idCategoria);

	

}
