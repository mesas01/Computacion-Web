package com.proyecto.entrega.dto;

public class CompanyDTO {
    private Long id;
    private String nombre;
    private String nit;
    private String email;
    private boolean activo;

    // Constructor vacío
    public CompanyDTO() {}

    // Constructor con todos los campos
    public CompanyDTO(Long id, String nombre, String nit, String email, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.nit = nit;
        this.email = email;
        this.activo = activo;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}