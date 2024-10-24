package com.tecgurus.puntoventa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tecgurus.puntoventa.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	/***
	 * findBy where Nombre nombre de la columna a buscar (Debe ser completo)
	 * 
	 * @param nombre
	 * @return
	 */
	List<Cliente> findByNombre(String nombre);

}
