package com.tecgurus.puntoventa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecgurus.puntoventa.config.Constantes;
import com.tecgurus.puntoventa.dto.ClienteDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.ResponseDeleteDTO;
import com.tecgurus.puntoventa.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Cliente", 
	 description = "Servicio que maneja a los clientes")
@RestController
@RequestMapping(Constantes.API + "cliente")
@CrossOrigin
public class ClienteController {
	
	@Autowired
	private ClienteService clienteS; // cltService, service, clienteService.
	
	/***
	 * Listar los clientes registrados.
	 * @return lista de clientes.
	 */
	@Operation(summary = "Lista de los clientes registrados.")
	@ApiResponses({
		@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consulta realizada correctamente!",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = ClienteDTO.class))}),
		@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content),
		@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description  = Constantes.UNAUTHORIZED_V, content = @Content),
		@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content),
		@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content),
		@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	})
    @GetMapping
	public List<ClienteDTO> listarClientes() {
		return clienteS.obtenerClientes();
	}


    @Operation(summary = "Obtener cliente registrado por identificador.")
	@ApiResponses({
		@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consulta realizada correctamente!",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = ClienteDTO.class))}),
		@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content),
		@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description  = Constantes.UNAUTHORIZED_V, content = @Content),
		@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content),
		@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content),
		@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	})
    @GetMapping("/{idCliente}")
    List<ClienteDTO> listaClienteID(@PathVariable Integer idCliente) {
        return clienteS.listaClienteId(idCliente);
    }
	
	/**
	 * Metodo que crear un cliente.
	 * @param clienteDTO datos del cliente
	 * @return un cliente nuevo.
	 */
	@Operation(summary = "Servicio que registra un nuevo cliente.")
	@ApiResponses({
		@ApiResponse(responseCode = Constantes.SUCCESS, description = "Registro de cliente exitoso!",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = ClienteDTO.class))}),
		@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content),
		@ApiResponse(responseCode = Constantes.OK, description = Constantes.OK_V, content = @Content),
		@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description  = Constantes.UNAUTHORIZED_V, content = @Content),
		@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content),
		@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content),
		@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	})
	@PostMapping
	public ClienteDTO agregarCliente(
			@Parameter(name = "cliente")
			@Valid @RequestBody final ClienteDTO cliente) {
		return clienteS.agregaCliente(cliente);
	}
	
	/**
	 * Metodo que actualiza un cliente existente.
	 * @param cliente datos del cliente.
	 * @param idCliente identificador del cliente.
	 * @return retorna un cliente actualizado.
	 */
	@Operation(summary = "Servicio que actualiza un cliente.")
	@ApiResponses({
		@ApiResponse(responseCode = Constantes.SUCCESS, description = "Se actualizo la informacion correctamente!",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = ClienteDTO.class))}),
		@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content),
		@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description  = Constantes.UNAUTHORIZED_V, content = @Content),
		@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content),
		@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content),
		@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	})
	@PutMapping("/{idCliente}")
	public ResponseDTO actualizaCliente(
			@Parameter(name = "cliente")
			@Valid @RequestBody final ClienteDTO cliente, 
			@Parameter(name = "idCliente", description = "Identificador del cliente", example = "1")
			@PathVariable final Integer idCliente) {
		return clienteS.actualizarCliente(cliente, idCliente);
	}
	
	/**
	 * Metodo que elimina complementa el registro del cliente.
	 * @param idCliente identificador del cliente.
	 * @return respuesta ok o error.
	 */
	@Operation(summary = "Servicio que eliminar un cliente definitivamente.")
	@ApiResponses({
		@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consulta realizada correctamente!",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = ResponseDTO.class))}),
		@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content),
		@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description  = Constantes.UNAUTHORIZED_V, content = @Content),
		@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content),
		@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content),
		@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	})
	@DeleteMapping("/{idCliente}")
	public ResponseDeleteDTO eliminaCliente(
			@Parameter(name = "idCliente", description = "identificador del cliente.", example = "1")
			@PathVariable final Integer idCliente) {
		return clienteS.eliminarCliente(idCliente);
		
	}
	
	/***
	 * 
	 * @param cliente
	 * @return
	 */
	@Operation(summary = "Busqueda de cliente por nombre completo.")
	@ApiResponses({
		@ApiResponse(responseCode = Constantes.SUCCESS, description = "Consulta realizada correctamente!",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = ClienteDTO.class))}),
		@ApiResponse(responseCode = Constantes.BAQ_REQUEST, description = Constantes.BAQ_REQUEST_V, content = @Content),
		@ApiResponse(responseCode = Constantes.UNAUTHORIZED, description  = Constantes.UNAUTHORIZED_V, content = @Content),
		@ApiResponse(responseCode = Constantes.FORBIDDEN, description = Constantes.FORBIDDEN_V, content = @Content),
		@ApiResponse(responseCode = Constantes.NOT_FOUND, description = Constantes.NOT_FOUND_V, content = @Content),
		@ApiResponse(responseCode = Constantes.UNEXPECTED_ERROR, description = Constantes.UNEXPECTED_ERROR_V, content = @Content)
	})
	// {} lo que se encuentre dentro de una variable tipo cadena debe de ser completo. (cadena completa).
	@GetMapping("/busqueda/{cliente}")
	public List<ClienteDTO> buscarClienteNombre(
			@Parameter(name = "cliente", description = "Nombre completo del cliente", example = "Miguel, Juan Pedro")
			@PathVariable final String cliente) {
		return clienteS.buscaClienteNombre(cliente);
	}
	
	
	

}
