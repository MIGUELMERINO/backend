package com.tecgurus.puntoventa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Setter
@Getter
public class Cliente {

	@Id
	@Column(name = "idcliente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;
	@Column(length = 150)
	private String nombre;
	@Column(length = 150)
	private String apaterno;
	@Column(length = 150)
	private String amaterno;
	@Column(length = 30)
	private String rfc;
	
	
	
}
