package com.proyecto.entrega.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GatewayDTO {

    private Long id;

    @NotEmpty(message = "El tipo de gateway no puede estar vacío")
    @Pattern(regexp = "EXCLUSIVO|PARALELO|INCLUSIVO", message = "El tipo debe ser EXCLUSIVO, PARALELO o INCLUSIVO")
    private String tipo;

    @NotNull(message = "El ID del proceso es obligatorio")
    private Long processId;
}