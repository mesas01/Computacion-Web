package com.example.tallerwiki.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String apellidos;
    private String correo;
    private String semestre;
    private String mensaje;
    public User(Long id,String nombre, String apellidos, String correo, String semestre, String mensaje) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.semestre = semestre;
        this.mensaje = mensaje;
    }
    public User(String nombre, String apellidos, String correo, String semestre, String mensaje) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.semestre = semestre;
        this.mensaje = mensaje;
    }
    public User(){
        
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getSemestre() {
        return semestre;
    }
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
