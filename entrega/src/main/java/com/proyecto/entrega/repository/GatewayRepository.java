package com.proyecto.entrega.repository;

import com.proyecto.entrega.entity.Gateway;
import com.proyecto.entrega.entity.Gateway.TipoGateway;
import com.proyecto.entrega.entity.Gateway.EstadoGateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatewayRepository extends JpaRepository<Gateway, Long> {

    // Consultas que respetan @Where (solo ACTIVOS)
    List<Gateway> findByProcessId(Long processId);               // Por proceso
    List<Gateway> findByProcessIdOrderByIdAsc(Long processId);   // Por proceso, ordenados

    // Por proceso y tipo (EXCLUSIVO/PARALELO/INCLUSIVO)
    List<Gateway> findByProcessIdAndTipo(Long processId,TipoGateway tipo);

    // Por proceso y estado (PENDIENTE/APROBADO/RECHAZADO)
    List<Gateway> findByProcessIdAndEstado(Long processId,EstadoGateway estado);

    long countByProcessId(Long processId);                       // Contar gateways de un proceso

    //  Incluir INACTIVOS (saltan @Where)
    // Requiere tabla "gateway" con columna "status".
    @Query(value = "SELECT * FROM gateway WHERE process_id = :processId ORDER BY id ASC", nativeQuery = true)
    List<Gateway> findAllByProcessIncludingInactive(@Param("processId") Long processId);

    // 'tipo' se persiste como texto; el Enum se serializa a su nombre (toString)
    @Query(value = "SELECT * FROM gateway WHERE process_id = :processId AND tipo = :tipo ORDER BY id ASC", nativeQuery = true)
    List<Gateway> findByProcessAndTipoIncludingInactive(@Param("processId") Long processId,
                                                        @Param("tipo") String tipo);
}
