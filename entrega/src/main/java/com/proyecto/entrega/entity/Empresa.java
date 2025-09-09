package com.proyecto.entrega.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "empresa") // explícito para alinear con SQLDelete
@Where(clause = "status = 'active'") // Sólo empresas activas en consultas normales
@SQLDelete(sql = "UPDATE empresa SET status = 'inactive' WHERE id = ?") // Borrado lógico
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// PK autoincremental

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String nit;

    @Column(name = "correo_contacto", nullable = false)
    private String correoContacto;

    //Por defecto, @OneToMany es LAZY en JPA 2.x+

    // 1 Empresa -> N Procesos
    // mappedBy="empresa" indica que la FK viene de Process
    // cascade=ALL: operar Empresa propagará a sus Process (persist, remove, etc.)
    // orphanRemoval=true: si se saca (borra) un Process de la lista, JPA lo elimina (huérfano)
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Process> procesos;

    // 1 Empresa -> N Roles
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rol> roles;

    // 1 Empresa -> N Usuarios
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Usuario> usuarios;

    // soft delete
    @Column(nullable = false, length = 16)
    private String status = "active";
}

//la tabla de ellos apunta a edge
//long nit puede perder ceros