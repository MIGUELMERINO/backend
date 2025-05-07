package com.tecgurus.puntoventa.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;

// escuchar un evento dentro del servicio o aplicativo.
@RestControllerAdvice
public class HandlerValidator {

	/***
	 * Metodo para visualizar mejor los error del spring-boot-valid
	 * 
	 * @param ex exception complemente del la clase.
	 * @return una mejor visualizacion del error o errores.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> methodArgumentNotFoundException(@NonNull final MethodArgumentNotValidException ex) {
		try {
			String mensage = ex.getFieldError().getDefaultMessage();
			return errros(validaNull(mensage), HttpStatus.UNAUTHORIZED);
		} catch (NullPointerException e) {
			return new HashMap<>();
		}
	}

	/**
	 * Captura las excepciones de entity not found que tenga mi aplicativo.
	 * 
	 * @param ex mensaje de error que envia spring boot.
	 * @return objeto con la descripcion del error.
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public Map<String, String> handlerEntityNotFoundException(final EntityNotFoundException ex) {
		return errros(ex.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	/***
	 * Metodo de mensaje Http.
	 * 
	 * @param ex el error que se obtiene
	 * @return un mensaje en un objeto JSON.
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String, String> httpMenssage(final HttpMessageNotReadableException ex) {
		return errros(ex.getMessage(), HttpStatus.UNAUTHORIZED);
	}

    /***
     *  Metodo para mostrar errores 400
     *	@param ex Exception que se obtiene
     *	@param request valor del request
     *  @return mensaje de error para que se pueda generalizar.
     ***/
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Map<String, String> hpptNotFound(Exception ex, WebRequest request) {
		return errros(Constantes.NOT_FOUND_V, HttpStatus.NOT_FOUND);
	}

	/**
	 * Metodo generar para capturar los errroes.
	 * 
	 * @param message mensage a mostar al usuario.
	 * @return map con los datos del error.
	 **/
	public Map<String, String> errros(final String message, final HttpStatus status) {
		Map<String, String> error = new HashMap<>();
		error.put("clave", status.toString());
		error.put("valor", message);
		return error;

	}

	/**
	 * Metodo para validar el valor en null.
	 * 
	 * @param message envio del valor.
	 * @return mensaje para aviso al usuario.
	 **/
	public String validaNull(final String message) {
		String mensaje = "Error Interno del servicio";
		if (message != null && !message.isEmpty()) {
			mensaje = message;
			return mensaje;
		}
		return mensaje;
	}

}
