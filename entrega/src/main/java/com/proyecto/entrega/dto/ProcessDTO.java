package com.proyecto.entrega.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessDTO {

    private Long id;

    @NotEmpty(message = "El nombre del proceso no puede estar vacío")
    private String name;

    private String description;

    @NotEmpty(message = "La categoría no puede estar vacía")
    private String category;

    private String status;

    @NotNull(message = "El ID de la empresa es obligatorio")
    private Long empresaId;
}