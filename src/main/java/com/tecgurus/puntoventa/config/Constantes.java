package com.tecgurus.puntoventa.config;

// se le coloca final por que nunca esta clase va a cambiar los datos.
public final class Constantes {

	private Constantes() {
	}

	// valors de los estatus
	public static final String SUCCESS = "200"; // estatus exitoso
	public static final String OK = "201"; // creado exitosamente.
	public static final String BAQ_REQUEST = "400"; // no encontrado
	public static final String UNAUTHORIZED = "401"; // no autorizado
	public static final String FORBIDDEN = "403"; // acceso prohibido
	public static final String NOT_FOUND = "404"; // no funciona o no existe.
	public static final String UNEXPECTED_ERROR = "500"; // error en el servidor o error interno del servidor.

	// signicado de cada valor.
	public static final String OK_V = "Creado exitosamente!";
	public static final String BAQ_REQUEST_V = "Existe campos obligatorios";
	public static final String UNAUTHORIZED_V = "Credenciales invalidas";
	public static final String FORBIDDEN_V = "Accesso prohibido";
	public static final String NOT_FOUND_V = "No se encuentra el servicio (datos)";
	public static final String UNEXPECTED_ERROR_V = "Ocurrio un error " + "en el sistema, intentarlo mas tarde.";

	// mensaje para las validaciones con spring-validation
	public static final String NOTNULL = "Este atributo es requerido!";
	public static final String NOTBLANK = "Este atributo no debe ir en blanco ";
	public static final String DESCRIPTION = "puedes colocar sin descripcion";

	// mensaje para el usuario o el desarrollador front end.
	public static final String SUCCESS_CREATE = "Registro exitoso!";
	public static final String SUCCESS_UPDATE = "Registro actualizado exitosamente!";
	public static final String SUCCESS_READ = "Consulta exitosa!";
	public static final String SUCCESS_DELETE = "Registro eliminado exitosamente!";
	public static final String ERROR = "El resgistro a buscar" + " no se encuentra disponible!";

	// informacion de swagger para el header (encabezado)
	public static final String TITLE = "Api punto de venta.";
	public static final String VERSION = "0.1";
	public static final String DESCRIPTION_SWAGGER = "Punto de " + "venta para proyecto personal";

	// configuracion api
	public static final String API = "/api/v1/";

	// expresiones regular para validar atributos.
	public static final String VALID_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";

}
