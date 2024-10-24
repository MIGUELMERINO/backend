package com.tecgurus.puntoventa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "categoria")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor 
public class Categoria {
	
	@Id
	@Column(name = "idcategoria")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategoria;
	@Column(length = 100, nullable = true)
	private String nombre;
	@Column(length = 600)
	private String descripcion;

}
