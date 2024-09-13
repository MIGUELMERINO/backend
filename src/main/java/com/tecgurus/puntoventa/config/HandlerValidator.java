package com.tecgurus.puntoventa.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.persistence.EntityNotFoundException;


// escuchar un evento dentro del servicio o aplicativo.
@RestControllerAdvice
public class HandlerValidator {
	
	/***
	 * Metodo para visualizar mejor los error del spring-boot-valid
	 * @param ex exception complemente del la clase.
	 * @return una mejor visualizacion del error o errores.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> methodArgumentNotFoundException(final MethodArgumentNotValidException ex) {
        return errros(ex.getFieldError().getDefaultMessage());
    }
	
	/**
	 * Captura las excepciones de entity not found que tenga mi aplicativo.
	 * @param ex mensaje de error que envia spring boot.
	 * @return objeto con la descripcion del error.
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public Map<String, String> handlerEntityNotFoundException(final EntityNotFoundException ex) {
	    return errros(ex.getMessage());
    }
	
	/***
	 * Metodo de mensaje Http.
	 * @param ex el error que se obtiene
	 * @return un mensaje en un objeto JSON.
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String, String> httpMenssage(final HttpMessageNotReadableException ex) {
	    return errros(ex.getMessage());
    }

    /**
     * Metodo generar para capturar los errroes.
     * @param message mensage a mostar al usuario.
     * @return map con los datos del error.
     * **/
    public Map<String, String> errros(String message) {
        Map<String, String> error = new HashMap<>();
		error.put("clave", Constantes.UNEXPECTED_ERROR);
		error.put("valor", message);
		return error;

    }

}
