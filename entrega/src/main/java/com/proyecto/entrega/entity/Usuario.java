package com.proyecto.entrega.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
// Filtra automáticamente para que SOLO se lean registros "active"
@Where(clause = "status = 'active'")
// Al hacer delete() en JPA, realmente se hara un UPDATE a status='inactive'
@SQLDelete(sql = "UPDATE usuario SET status = 'inactive' WHERE id = ?")
@Table(name = "usuario") // debe ser asi en la tabla, ojo
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Esto ayuda a proteger la unicidad de "correo" a nivel de BD, el unique
    @Column(nullable = false, unique = true)
    private String correo;

    // nullable = false significa que ese campo no puede ser nulo
    @Column(nullable = false)
    private String password;

    // LAZY reduce carga al traer Usuario sin necesidad de traer Empresa/Rol
    // por defecto es EAGER en @ManyToOne en JPA, puede traer más datos de los necesarios.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    // Muchos usuarios tienen un rol; LAZY = carga diferida
    //Lazy solo carga el Rol cuando se necesita, no de inmediato
    //solo carga el Rol cuando se accede a él, no inmediatamente
    //Evita traer datos innecesarios al consultar usuarios.
    @ManyToOne(fetch = FetchType.LAZY) // Muchos usuarios tienen un rol; LAZY = carga diferida
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    // Campo de estado para soft delete (esto no esta en la version dev del equipo).
    // Valores típicos: "active" / "inactive".
    @Column(nullable = false, length = 16)
    private String status = "active";
}