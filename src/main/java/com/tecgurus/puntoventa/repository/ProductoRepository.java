package com.tecgurus.puntoventa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.tecgurus.puntoventa.entity.Producto;
import jakarta.transaction.Transactional;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	/**
	 * Modifying -> modifica internamente la consulta para que la tome como
	 * principal al momento de ejecutarla. Transacional -> operacion (insertar,
	 * eliminar, consultar y actualizar) que realiza hibernate para los datos.
	 * 
	 * @param idProducto identificador del producto.
	 */
	@Transactional
	@Modifying
	@Query(value = "delete from producto where idproducto = :idProducto", nativeQuery = true)
	void findByElimnaProducto(@Param("idProducto") Integer idProducto);

	@Query(value = "SELECT * FROM producto where nombre like %:nombre% ", nativeQuery = true)
	List<Producto> busquedaProducto(@Param("nombre") String nombre);

}
