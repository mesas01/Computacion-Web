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
@Table(name = "process") // se fija el nombre real de la tabla para evitar conflictos con palabras reservadas
@Where(clause = "status = 'active'") // Solo retorna procesos activos
@SQLDelete(sql = "UPDATE process SET status = 'inactive' WHERE id = ?") // Soft delete
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String category;

    @Column(nullable = false, length = 16)    // No-nulo
    private String status = "active";         // Default activo: al crear, ya queda visible por @Where

    @ManyToOne(fetch = FetchType.LAZY)// N:1 -> muchos Process pertenecen a una Empresa
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;// FK obligatoria a Empresa

    //para cascade referirse a empresa, alli esta la descripcion
    // 1:N con Activity. mappedBy="process" => la FK está en Activity.process
    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Activity> activities;

    // 1:N con Edge
    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Edge> edges;

    // 1:N con Gateway
    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Gateway> gateways;
}