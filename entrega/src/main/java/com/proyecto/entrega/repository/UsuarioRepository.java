package com.proyecto.entrega.repository;

import com.proyecto.entrega.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;// Para consultas nativas
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// Nota: Usuario tiene @Where(status='active'), por defecto TODOS los find... ignoran usuarios inactivos.
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //Consultas de negocio comunes
    Optional<Usuario> findByCorreo(String correo);           // Buscar por correo (exacto, case-sensitive)
    List<Usuario> findByEmpresaId(Long empresaId);           // Listar por empresa (solo activos)
    boolean existsByCorreo(String correo);                   // Verificar duplicado por correo

    //Variantes útiles

    Optional<Usuario> findByCorreoIgnoreCase(String correo); // Buscar por correo (case-insensitive)
    boolean existsByCorreoIgnoreCase(String correo);         // Existe correo (case-insensitive)

    List<Usuario> findByEmpresaIdOrderByIdAsc(Long empresaId); // Listar ordenado por id asc

    // para Incluir a los INACTIVOS (saltan el @Where)
    // Úsalas solo para auditoría/administración.

    @Query(value = "SELECT * FROM usuario WHERE correo = :correo LIMIT 1", nativeQuery = true)
    Optional<Usuario> findByCorreoIncludingInactive(@Param("correo") String correo);

    @Query(value = "SELECT * FROM usuario WHERE empresa_id = :empresaId ORDER BY id ASC", nativeQuery = true)
    List<Usuario> findAllByEmpresaIncludingInactive(@Param("empresaId") Long empresaId);
}


/*
* Funciona para CRUD básico.
* No aporta consultas de negocio (no hay findByEmail, findByCompanyId, etc.).
* Usa User y no añade restricciones/convenciones
* No marcan @Repository.*/