package com.proyecto.entrega.repository;

import com.proyecto.entrega.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    // Consultas básicas de dominio

    List<Rol> findByEmpresaId(Long empresaId);                    // Roles por empresa (si hay @Where, solo activos)

    List<Rol> findByEmpresaIdOrderByNombreAsc(Long empresaId);    // Roles por empresa ordenados por nombre

    Optional<Rol> findByNombreIgnoreCaseAndEmpresaId(             // Buscar un rol por nombre (case-insensitive) y empresa
            String nombre, Long empresaId
    );

    boolean existsByNombreIgnoreCaseAndEmpresaId( // Validar duplicado de nombre dentro de la empresa
            String nombre, Long empresaId
    );

    // Incluir INACTIVOS (saltan @Where)

    @Query(value = "SELECT * FROM rol WHERE empresa_id = :empresaId ORDER BY nombre ASC", nativeQuery = true)
    List<Rol> findAllByEmpresaIncludingInactive(@Param("empresaId") Long empresaId);

    @Query(value = "SELECT * FROM rol WHERE empresa_id = :empresaId AND LOWER(nombre) = LOWER(:nombre) LIMIT 1", nativeQuery = true)
    Optional<Rol> findByNombreIncludingInactive(
            @Param("empresaId") Long empresaId,
            @Param("nombre") String nombre
    );
}
