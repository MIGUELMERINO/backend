package com.tecgurus.puntoventa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "producto")
@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

	@Id
	@Column(name = "idproducto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	@Column(length = 200)
	private String nombre;
	@Column(length = 600)
	private String descripcion;
	private Double precio;
	private Integer stock;
	private String sku;
	private String imagen;
	// Many -> Muchos To -> enlace One -> uno
	// EAGER -> carga los datos relacione si o si
	// LAZY -> se carga si se llama o se invoca.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcategoria")
	private Categoria categoria;
	/**
	 * select * from producto p join categoria c on p.idcategoria = c.idcategoria;
	 */

}
