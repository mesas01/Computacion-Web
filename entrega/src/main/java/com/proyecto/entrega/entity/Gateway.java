package com.proyecto.entrega.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@Table(name = "gateway")// Fija el nombre para alinear con @SQLDelete
@Where(clause = "status = 'active'")// Solo gateways activos
@SQLDelete(sql = "UPDATE gateway SET status = 'inactive' WHERE id = ?")
public class Gateway {

    public enum TipoGateway { EXCLUSIVO, PARALELO, INCLUSIVO }// Tipo controlado

    // Estado de negocio tipado, en vez de String libre "aceptado/no aceptado"
    public enum EstadoGateway { PENDIENTE, APROBADO, RECHAZADO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)// Guarda enum como texto
    @Column(nullable = false)
    private TipoGateway tipo;

    // Soft delete: campo requerido por @Where/@SQLDelete
    @Column(nullable = false, length = 16)
    private String status = "active";

    // Estado de negocio del gateway
    @Enumerated(EnumType.STRING)
    private EstadoGateway estado; // Opcional: PENDIENTE/APROBADO/RECHAZADO

    @ManyToOne(fetch = FetchType.LAZY)// Muchos gateways → 1 proceso (carga diferida)
    @JoinColumn(name = "process_id", nullable = false)
    private Process process;
}