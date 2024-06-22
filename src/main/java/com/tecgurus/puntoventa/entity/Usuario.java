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
@Table(name = "usuario")
@Setter
@Getter
public class Usuario {
	
	
	@Id
	@Column(name = "idusuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	@Column(length = 255, unique = true)
	private String email;
	@Column(length = 255)
	private String password;
	@Column(length = 150)
	private String nombre;
	@Column(length = 150)
	private String apaterno;
	@Column(length = 150)
	private String amaterno;
	private Integer activo; // 0 inactivo, 1 activo, 2 cancelado estatos logicos de los datos.
	@Column(length = 50)
	private String perfil;
    
}
