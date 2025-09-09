package com.proyecto.entrega.repository;

import com.proyecto.entrega.entity.Edge;
import com.proyecto.entrega.entity.Edge.NodeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EdgeRepository extends JpaRepository<Edge, Long> {

    //Consultas que respetan @Where (solo ACTIVAS)
    List<Edge> findByProcessId(Long processId);                  // Aristas de un proceso
    List<Edge> findByProcessIdOrderByIdAsc(Long processId);      // Aristas ordenadas

    List<Edge> findByProcessIdAndSourceId(Long processId, Long sourceId); // Por origen
    List<Edge> findByProcessIdAndTargetId(Long processId, Long targetId); // Por destino
    List<Edge> findByProcessIdAndSourceType(Long processId, NodeType sourceType); // Origen por tipo
    List<Edge> findByProcessIdAndTargetType(Long processId, NodeType targetType); // Destino por tipo

    long countByProcessId(Long processId);                       // Contar aristas del proceso

    // ===== Incluir INACTIVAS (saltan @Where) =====
    // Requiere tabla "edge" con columna "status".
    @Query(value = "SELECT * FROM edge WHERE process_id = :processId ORDER BY id ASC", nativeQuery = true)
    List<Edge> findAllByProcessIncludingInactive(@Param("processId") Long processId);

    @Query(value = """
                   SELECT * FROM edge 
                   WHERE process_id = :processId 
                     AND source_id  = :sourceId 
                     AND source_type = :sourceType 
                   ORDER BY id ASC
                   """, nativeQuery = true)
    List<Edge> findByProcessAndSourceIncludingInactive(@Param("processId") Long processId,
                                                       @Param("sourceId") Long sourceId,
                                                       @Param("sourceType") String sourceType); // usar sourceType=name() del enum
}
