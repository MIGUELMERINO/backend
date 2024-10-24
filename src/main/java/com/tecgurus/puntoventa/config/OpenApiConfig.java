package com.tecgurus.puntoventa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	/***
	 * Metodo que asigna un valor de configuracion.
	 * 
	 * @return configuracion personalizada.
	 */
	@Bean
	public OpenAPI opeApi() {
		return new OpenAPI().info(getInfo());
	}

	/***
	 * Metedo que sobreescribe los datos principales.
	 * 
	 * @return dicha configuracion personalizada.
	 */
	private Info getInfo() {
		return new Info().title(Constantes.TITLE).version(Constantes.VERSION)
				.description(Constantes.DESCRIPTION_SWAGGER);
	}

}
