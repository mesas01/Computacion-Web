package com.proyecto.entrega.dto;

public class UsuarioDTO {

    private Long id;
    private String correo;
    private String password; // Nota: No devolver el password en las respuestas de la API
    private Long empresaId;
    private Long rolId;

    // Constructores
    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String correo, Long empresaId, Long rolId) {
        this.id = id;
        this.correo = correo;
        this.empresaId = empresaId;
        this.rolId = rolId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }
}
