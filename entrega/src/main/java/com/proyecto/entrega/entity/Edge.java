package com.proyecto.entrega.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@Table(name = "edge")// Nombre de tabla explícito
@Where(clause = "status = 'active'")// Solo registros activos
@SQLDelete(sql = "UPDATE edge SET status = 'inactive' WHERE id = ?") // Soft delete
public class Edge {

    // Tipos de nodo permitidos (evita strings arbitrarios)
    public enum NodeType { ACTIVITY, GATEWAY }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source_id", nullable = false)    // ID del nodo origen (Activity/Gateway)
    private Long sourceId;

    @Column(name = "target_id", nullable = false)    // ID del nodo destino (Activity/Gateway)
    private Long targetId;

    @Enumerated(EnumType.STRING)// Persistir enum como texto
    @Column(name = "source_type", nullable = false)  // Tipo del origen (ACTIVITY/GATEWAY)
    private NodeType sourceType;

    @Enumerated(EnumType.STRING)// Persistir enum como texto
    @Column(name = "target_type", nullable = false)  // Tipo del destino (ACTIVITY/GATEWAY)
    private NodeType targetType;

    @ManyToOne(fetch = FetchType.LAZY)// N aristas → 1 proceso (carga diferida)
    @JoinColumn(name = "process_id", nullable = false) // FK a Process obligatoria
    private Process process;

    @Column(nullable = false, length = 16)
    private String status = "active";
}
