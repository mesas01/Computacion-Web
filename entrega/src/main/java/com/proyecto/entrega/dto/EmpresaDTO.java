package com.proyecto.entrega.dto;

public class EmpresaDTO {

    private Long id;
    private String nombre;
    private String nit;
    private String correoContacto;

    // Constructores
    public EmpresaDTO() {
    }

    public EmpresaDTO(Long id, String nombre, String nit, String correoContacto) {
        this.id = id;
        this.nombre = nombre;
        this.nit = nit;
        this.correoContacto = correoContacto;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }
}
