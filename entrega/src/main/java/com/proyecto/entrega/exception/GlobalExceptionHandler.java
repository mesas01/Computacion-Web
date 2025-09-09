package com.proyecto.entrega.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

//Cómo conecta con el resto
/*
* 1 Services lanzan NotFoundException/DuplicateResourceException
* 2 Controllers no necesitan try/catch: el advice lo hace por ellos
* 3 DTOs con anotaciones de validación (@NotEmpty, @Email, etc.) disparan
* MethodArgumentNotValidException cuando algo no cumple
*/
@ControllerAdvice
public class GlobalExceptionHandler {

    // Maneja cuando se lanza NotFoundException en cualquier parte del flujo.
    // Responde 404 con un cuerpo JSON estándar (ErrorResponse).
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        ErrorResponse error = new ErrorResponse("ERROR_NOT_FOUND", e.getMessage());
        // 404 Not Found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Maneja cuando hay recursos duplicados (2 id iguales).
    // Responde 409 con un ErrorResponse.
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException e) {
        ErrorResponse error = new ErrorResponse("ERROR_DUPLICATE", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // Maneja errores de validación de DTOs (usando @Valid en controladores).
    // Devuelve un mapa campo -> mensaje de error, con 400 Bad Request.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}