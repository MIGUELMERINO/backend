package com.tecgurus.puntoventa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecgurus.puntoventa.entity.CompraProducto;

public interface CompraProductoRepository extends JpaRepository<CompraProducto, Integer> {

	@Query(value = "SELECT * FROM compraproducto where idcompra=:idCompra", nativeQuery = true)
	List<CompraProducto> busquedaCompra(@Param("idCompra") Integer idCompra);

}
