package com.proyecto.entrega.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

//define la forma del JSON de error que la API devuelve cuando algo falla.
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
}