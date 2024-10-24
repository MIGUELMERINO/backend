package com.tecgurus.puntoventa;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class PuntoventaApplication {

	/**
	 * Para colocar horario Ciudad_Mexico/centro en el servidor.
	 **/
	@PostConstruct
	public void started() {
		final int horas = 6;
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of(ZoneOffset.ofHours(-horas).getId())));
	}

	public static void main(String[] args) {
		SpringApplication.run(PuntoventaApplication.class, args);
	}

}
