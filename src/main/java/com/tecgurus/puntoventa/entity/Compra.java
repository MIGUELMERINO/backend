package com.tecgurus.puntoventa.entity;

import java.sql.Date;

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
@Table(name = "compra")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Compra {
	
	@Id
	@Column(name = "idcompra")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCompra;
	private Double total;
	private Date fecha;
	@ManyToOne
	@JoinColumn(name ="idusuario")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name = "idcliente")
	private Cliente cliente;

}
