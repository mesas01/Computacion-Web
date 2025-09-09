package com.proyecto.entrega.repository;

import com.proyecto.entrega.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    //Consultas de negocio (solo ACTIVAS)

    Optional<Empresa> findByNit(String nit);                       // Buscar por NIT (exacto)
    Optional<Empresa> findByNombreIgnoreCase(String nombre);       // Buscar por nombre (case-insensitive)
    Optional<Empresa> findByCorreoContactoIgnoreCase(String correoContacto); // Buscar por correo de contacto

    boolean existsByNit(String nit);                               // Validar duplicado por NIT
    boolean existsByNombreIgnoreCase(String nombre);               // Validar duplicado por nombre

    List<Empresa> findAllByOrderByNombreAsc();                     // Listar ordenado alfabéticamente

    // Consultas que INCLUYEN INACTIVOS (saltan el @Where de soft delete)
    // Nota: requieren que la tabla real se llame "empresa" y exista columna "status".

    @Query(value = "SELECT * FROM empresa WHERE nit = :nit LIMIT 1", nativeQuery = true)
    Optional<Empresa> findByNitIncludingInactive(@Param("nit") String nit);

    @Query(value = "SELECT * FROM empresa WHERE LOWER(nombre) = LOWER(:nombre) LIMIT 1", nativeQuery = true)
    Optional<Empresa> findByNombreIncludingInactive(@Param("nombre") String nombre);

    @Query(value = "SELECT * FROM empresa ORDER BY nombre ASC", nativeQuery = true)
    List<Empresa> findAllIncludingInactiveOrderByNombreAsc();
}
