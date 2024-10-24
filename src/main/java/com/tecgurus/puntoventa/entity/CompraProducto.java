package com.tecgurus.puntoventa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "compraproducto")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CompraProducto {
	// compra_producto, si no se configura la anotacion @Table.

	@Id
	@Column(name = "idcompraproducto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCompraProducto;
	@ManyToOne
	@JoinColumn(name = "idcompra")
	private Compra compra;
	@ManyToOne
	@JoinColumn(name = "idproducto")
	private Producto producto;
	private Integer cantidad;
	private Double costo;

}
