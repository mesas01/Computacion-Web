package com.proyecto.entrega.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityDTO {

    private Long id;

    @NotEmpty(message = "El nombre de la actividad no puede estar vacío")
    private String name;

    private String description;

    @NotEmpty(message = "El tipo de actividad no puede estar vacío")
    private String tipo;

    private String status;

    @NotNull(message = "El ID del proceso es obligatorio")
    private Long processId;

    @NotNull(message = "El ID del rol responsable es obligatorio")
    private Long rolResponsableId;
}