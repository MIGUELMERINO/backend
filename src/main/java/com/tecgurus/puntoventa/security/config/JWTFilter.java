package com.tecgurus.puntoventa.security.config;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tecgurus.puntoventa.security.service.JWTService;
import com.tecgurus.puntoventa.security.service.UserDatailServices;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

	private UserDatailServices userDetialServices;
	private JWTService jwtService;

	/**
	 * Primera capa para validar nuestro token creado y generado por spring security
	 * 
	 * @param request     solicitud http obtendremos los encabezados donde debe
	 *                    venir el token.
	 * @param response    respuesta que envia al cliente.
	 * @param filterChain filtado que realiza esta capa para validar token.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// la autorizacion en el encabezado o headers debe de ir si o si, sino no manda
		// un error 401
		final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authorization == null || !authorization.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		final String token = authorization.substring(7);
		final String email = jwtService.validaToken(token);
		if (email == null) {
			filterChain.doFilter(request, response);
			return;
		}

		final UserDetails userDetials = userDetialServices.loadUserByUsername(email);
		final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetials,
				null, userDetials.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);

	}

}
