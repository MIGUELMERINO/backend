package com.tecgurus.puntoventa.config;

// se le coloca final por que nunca esta clase va a cambiar los datos.
public final class Constantes {
	
	// valors de los estatus
	public final static String SUCCESS = "200"; // estatus exitoso
	public final static String OK = "201"; // creado exitosamente.
	public final static String BAQ_REQUEST = "400"; // no encontrado
	public final static String UNAUTHORIZED = "401"; // no autorizado
	public final static String FORBIDDEN = "403"; // acceso prohibido
	public final static String NOT_FOUND = "404"; // no funciona o no existe.
	public final static String UNEXPECTED_ERROR = "500"; // error en el servidor o error interno del servidor.

	// signicado de cada valor.
	public final static String OK_V = "Creado exitosamente!";
	public final static String BAQ_REQUEST_V = "Existe campos obligatorios";
	public final static String UNAUTHORIZED_V = "Credenciales invalidas";
	public final static String FORBIDDEN_V = "Accesso prohibido";
	public final static String NOT_FOUND_V = "No se encuentra el servicio (datos)";
	public final static String UNEXPECTED_ERROR_V = "Ocurrio un error "
			+ "en el sistema, intentarlo mas tarde.";
	
	// mensaje para las validaciones con spring-validation
	public final static String NOTNULL = "Este atributo no debe ser nulo!";
	public final static String NOTBLANK = "Este atributo no debe ir en blanco "
			+ "o con espacios en blanco";
	public final static String DESCRIPTION = "puedes colocar sin descripcion";
	
	// informacion de swagger para el header (encabezado)
	public final static String TITLE = "Api punto de venta.";
	public final static String VERSION = "0.1";
	public final static String DESCRIPTION_SWAGGER = "Punto de "
			+ "venta para proyecto personal";

    // configuracion api
    public final static String API = "/api/v1/"; 

	// expresiones regular para validar atributos.
	public final static String VALID_PASSWORD = 
			"^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
	
}
