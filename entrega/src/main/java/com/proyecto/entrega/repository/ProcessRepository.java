package com.proyecto.entrega.repository;

import com.proyecto.entrega.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {

    //Consultas de dominio (respetan @Where: solo ACTIVOS)

    List<Process> findByEmpresaId(Long empresaId);                 // Procesos por empresa (activos)

    List<Process> findByEmpresaIdOrderByNameAsc(Long empresaId);   // Procesos por empresa, ordenados por nombre

    Optional<Process> findByNameIgnoreCaseAndEmpresaId(            // Buscar por nombre (case-insensitive) dentro de la empresa
            String name, Long empresaId
    );

    boolean existsByNameIgnoreCaseAndEmpresaId( // Validar duplicado de nombre dentro de la empresa
            String name, Long empresaId
    );

    //Consultas que INCLUYEN INACTIVOS (saltan el @Where)
    // Nota: requieren que la tabla real se llame "process" y tenga columna "status".

    @Query(value = "SELECT * FROM process WHERE empresa_id = :empresaId ORDER BY name ASC", nativeQuery = true)
    List<Process> findAllByEmpresaIncludingInactiveOrderByNameAsc(@Param("empresaId") Long empresaId);

    @Query(value = "SELECT * FROM process WHERE empresa_id = :empresaId AND LOWER(name) = LOWER(:name) LIMIT 1", nativeQuery = true)
    Optional<Process> findByNameIncludingInactive(
            @Param("empresaId") Long empresaId,
            @Param("name") String name
    );
}
