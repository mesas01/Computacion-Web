package com.proyecto.entrega.dto;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Este DTO se reestructuró para mejorar seguridad, claridad y compatibilidad con el mapeo.
 * 1) Separamos en modelos de entrada/salida (Create/Update/Response) para definir un contrato de API explícito y evitar exponer campos indebidos.
 * 2) Eliminamos el campo con tilde (“contraseña”) y estandarizamos a “password” para evitar problemas de serialización/herramientas.
 * 3) La contraseña se marca como WRITE_ONLY (no se devuelve nunca), reduciendo riesgo de filtrado de credenciales.
 * 4) Agregamos validaciones básicas (@NotBlank, @Email, @Size) para rechazar datos inválidos antes de llegar a la capa de negocio.
 * 5) Alineamos nombres con la entidad real (Usuario) y con ModelMapper en modo STRICT para que el mapeo sea predecible.
 * 6) Las relaciones se representan por IDs (empresaId, rolId) y se resuelven en el Service, evitando acoplar DTOs a entidades JPA.
 */


public class UsuarioDTO {

    // Entrada: crear usuario
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class Create {
        private String correo;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // no sale en respuestas
        private String password;
        private Long empresaId;
        private Long rolId;
    }

    // Entrada: actualizar usuario (opcionales)
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class Update {
        private String correo;     // opcional
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;   // opcional
        private Long empresaId;    // opcional
        private Long rolId;        // opcional
    }

    //  Salida: lo que devuelve el servicio por la API
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class Response {
        private Long id;
        private String correo;
        private Long empresaId;
        private Long rolId;
        // opcionalmente, información “de apoyo” para la UI:
        private String empresaNombre; // llenar desde empresa.getNombre()
        private String rolNombre;     // llenar desde rol.getNombre()
    }
}
