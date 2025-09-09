package com.proyecto.entrega.repository;

import com.proyecto.entrega.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    //Consultas de dominio
    List<Activity> findByProcessId(Long processId);                // Actividades por proceso (activas)
    List<Activity> findByProcessIdOrderByIdAsc(Long processId);    // Actividades por proceso, ordenadas
    List<Activity> findByProcessIdAndRolResponsableId(             // Actividades por proceso y rol (si rol es opcional, devuelve solo las que lo tienen)
            Long processId, Long rolId
    );

    Optional<Activity> findByProcessIdAndNameIgnoreCase(        // Buscar por nombre dentro del proceso (case-insensitive)
            Long processId, String name
    );

    boolean existsByProcessIdAndNameIgnoreCase(                 // Validar duplicados de nombre en el proceso
            Long processId, String name
    );

    long countByProcessId(Long processId);                         // Contar actividades de un proceso (activas)

    // Consultas que INCLUYEN INACTIVAS (saltan el @Where)
    // Requiere tabla "activity" con columna "status".

    @Query(value = "SELECT * FROM activity WHERE process_id = :processId ORDER BY id ASC", nativeQuery = true)
    List<Activity> findAllByProcessIncludingInactive(@Param("processId") Long processId);

    @Query(value = "SELECT * FROM activity WHERE process_id = :processId AND LOWER(name) = LOWER(:name) LIMIT 1", nativeQuery = true)
    Optional<Activity> findByProcessAndNameIncludingInactive(
            @Param("processId") Long processId,
            @Param("name") String name
    );
}
