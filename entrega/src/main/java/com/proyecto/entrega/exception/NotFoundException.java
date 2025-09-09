package com.proyecto.entrega.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//excepción personalizada que se lanza cuando un recurso no existe en la base de datos
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    //Permite personalizar el mensaje del error (ej. “Empresa con id X no existe”)
    public NotFoundException(String message)
    {
        super(message);
    }
}