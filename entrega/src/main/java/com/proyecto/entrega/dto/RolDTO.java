package com.proyecto.entrega.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolDTO {

    private Long id;

    @NotEmpty(message = "El nombre del rol no puede estar vacío")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El ID de la empresa es obligatorio para asociar el rol")
    private Long empresaId;
}