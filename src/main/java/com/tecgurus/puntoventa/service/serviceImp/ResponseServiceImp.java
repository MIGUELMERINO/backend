package com.tecgurus.puntoventa.service.serviceImp;

import com.tecgurus.puntoventa.service.ResponseService;
import com.tecgurus.puntoventa.dto.ResponseDeleteDTO;
import com.tecgurus.puntoventa.dto.ResponseDTO;

import org.springframework.stereotype.Service;


@Service
public class ResponseServiceImp implements ResponseService {

    /**
     * Metodo que responde a los objetos como agregar, actualizar, listar, listar por ID.
     * @param mensaje mensaje para el usuario si este es un ingreso a algun controlador.
     * @param obj objeto a mostrar ya sea ingresado,lista o objeto.
     * @return una respuesta creada con la finalidad de enviar estatus, mensaje e informacion al usuario.
     * **/
    @Override
    public ResponseDTO response(final String mensaje, final Object obj) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setClave("200");
        responseDTO.setValor(mensaje);
        responseDTO.setPlayLoad(obj);
        return responseDTO;
    }

    /**
     * Metodo que response a las solicitudes de elimacion del objeto en la API.
     * @param mensaje aqui le informamos al usuario que se elimino el resgistro existosamente.
     * @return una respuesta creada con la finalidad de estructurar los mensaje de elimanacion de informacion al usuario.
     * **/
    @Override
    public ResponseDeleteDTO responseDelete(final String mensaje) {
        ResponseDeleteDTO responseDeleteDTO = new ResponseDeleteDTO();
        responseDeleteDTO.setClave("200");
        responseDeleteDTO.setValor(mensaje);
        return responseDeleteDTO;
    }
}

