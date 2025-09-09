package com.proyecto.entrega.exception;

//excepción personalizada que se lanza cuando alguien intenta crear un recurso que ya existe en la base de datos.
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}