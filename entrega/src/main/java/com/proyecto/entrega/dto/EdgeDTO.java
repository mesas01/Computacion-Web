package com.proyecto.entrega.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EdgeDTO {

    private Long id;

    @NotNull(message = "El ID de origen (sourceId) es obligatorio")
    private Long sourceId;

    @NotNull(message = "El ID de destino (targetId) es obligatorio")
    private Long targetId;

    @NotEmpty(message = "El tipo de origen (sourceType) no puede estar vacío")
    @Pattern(regexp = "activity|gateway", message = "El tipo de origen debe ser 'activity' o 'gateway'")
    private String sourceType;

    @NotEmpty(message = "El tipo de destino (targetType) no puede estar vacío")
    @Pattern(regexp = "activity|gateway", message = "El tipo de destino debe ser 'activity' o 'gateway'")
    private String targetType;

    @NotNull(message = "El ID del proceso es obligatorio")
    private Long processId;
}