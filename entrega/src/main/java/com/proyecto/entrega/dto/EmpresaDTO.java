package com.proyecto.entrega.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaDTO {
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotEmpty(message = "El NIT no puede estar vacío")
    private String nit;

    @NotEmpty(message = "El correo de contacto no puede estar vacío")
    @Email(message = "El formato del correo es inválido")
    private String correoContacto;
}