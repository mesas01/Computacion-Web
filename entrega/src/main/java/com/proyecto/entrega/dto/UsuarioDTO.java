package com.proyecto.entrega.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String correo;
    private Long empresaId;
    private Long rolId;

    // DTO solo para la creación,
    // el password no se expone en las respuestas
    @Getter
    @Setter
    public static class Create {
        @NotEmpty(message = "El correo no puede estar vacío")
        @Email(message = "El formato del correo es inválido")
        private String correo;

        @NotEmpty(message = "La contraseña no puede estar vacía")
        private String password;

        @NotNull(message = "El ID de la empresa es obligatorio")
        private Long empresaId;

        @NotNull(message = "El ID del rol es obligatorio")
        private Long rolId;
    }
}