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
	@Column(name = "idcategoria") // si no se coloca el @Column con el nombre automaticamente toma el nombre del atributo.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategoria; // si se deja asi se crea id_categoria;
	@Column(length = 100, nullable = true)
	private String nombre; // si no configura este al momento de crearce va a tomar el maximo valor de un varchar(Cadena o String) 255
	@Column(length = 600)
	private String descripcion;
	
	// internamente realiza esto create table categoria idcategoria Integer(5), nombre varchar(100), descripcion varchar(600)
	// Nota: el maximo valo de una tabla en caracteres es de 25790 que se restan de acuerdo a cada atributo de tipo varchar registrado en dicha tabla.
	

}
