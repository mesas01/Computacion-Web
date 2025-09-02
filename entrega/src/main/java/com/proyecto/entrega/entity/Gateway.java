package com.proyecto.entrega.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Gateway {

    // aquí un Enum para asegurar que solo valores válidos sean posibles.
    public enum TipoGateway {
        EXCLUSIVO,
        PARALELO,
        INCLUSIVO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Guarda como String en la base de datos
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoGateway tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id", nullable = false)
    private Process process;
}