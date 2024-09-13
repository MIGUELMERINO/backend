package com.tecgurus.puntoventa.service;

import com.tecgurus.puntoventa.dto.ResponseDTO;
import com.tecgurus.puntoventa.dto.ResponseDeleteDTO;


public interface ResponseService {
    
    /**
     * Metodo para ocupar el ResponseDTO para nuestra api.
     * @param mensaje mensaje para el usuario.
     * @param obj objeto o clase que se debe de mostrar.
     * @return Objeto de tipo ResponseDTO.
     * **/
    ResponseDTO response(String mensaje, Object obj);

    /**
     * Metodo para enviar un ResponseDeleteDTO para el usuario de eliminacion.
     * @param mensaje mensaje a mostar en el valor del objeto
     * @return objeto de tipo ResponseDeleteDTO para eliminar un objeto.
     * **/
    ResponseDeleteDTO responseDelete(String mensaje);

}


