package com.proyecto.entrega.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@Table(name = "activity")
@Where(clause = "status = 'active'")// Solo actividades activas en consultas normales
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String tipo;

    @Column(nullable = false, length = 16)         // No nulo, soft delete
    private String status = "active";              // Default activo

    // Solo si se quiere guardar el layout del diagrama: (preguntar que es esto)
    // @Column private Double x;
    // @Column private Double y;
    // @Column private Double width;
    // @Column private Double height;

    // Muchas actividades pertenecen a UN proceso
    @ManyToOne(fetch = FetchType.LAZY)// LAZY consultar que signiufica en rol, usuario
    @JoinColumn(name = "process_id", nullable = false)// nullable = false = NO se puede crear una Activity sin asociarla a un Process.
    private Process process;

    // Muchas actividades PUEDEN tener UN rol responsable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id")// Como NO tiene nullable=false, este FK puede ser NULL  puede no tener rol asignado todavía
    private Rol rolResponsable;
}