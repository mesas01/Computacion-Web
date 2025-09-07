package com.proyecto.entrega.repository;

import com.proyecto.entrega.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessRepository extends JpaRepository<Process, Long> {
    List<Process> findByEmpresaId(Long empresaId);
}