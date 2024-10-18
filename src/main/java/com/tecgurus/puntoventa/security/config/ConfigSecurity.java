package com.tecgurus.puntoventa.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import com.tecgurus.puntoventa.config.Constantes;

@Configuration
@EnableMethodSecurity // hablitar la configuracion de spring security
public class ConfigSecurity {

	@Autowired
	private JWTFilter jwtFilter;
	
	/***
	 * Metodo que crea un enlace con el encriptador de BCrypt
	 * @return un forma de encriptacion.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/***
	 * Metodo que configura la auntentificacion de usuario.
	 * @param authenticationConfiguration configuracion.
	 * @return un atentificacion personalizada.
	 * @throws Exception exception o error al momento del proceso.
	 */
	@Bean
	public AuthenticationManager authenticationManager(
			final AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	/***
	 * Metodo que realiza el filtrado y permisos de los usuarios registrados. 
	 * @param http configuracion de http security.
	 * @return una session y permisos del back end.
	 * @throws Exception envia una excepcion por algun error.
	 */
	@Bean
	public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
		return http.cors(withDefaults())
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/api/v1/auth/**", "/v3/api-docs/**", "/swagger-ui/**")
						.permitAll()
						.requestMatchers(Constantes.API + "authentication").permitAll()
						.anyRequest().authenticated()) // los demas servicios o path debe de llevar el filter y la autorizacion
				.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	
}
