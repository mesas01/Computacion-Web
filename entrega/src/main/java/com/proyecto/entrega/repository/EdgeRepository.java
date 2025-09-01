package com.proyecto.entrega.repository;

import com.proyecto.entrega.entity.Edge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EdgeRepository extends JpaRepository<Edge, Long> {
    List<Edge> findByProcessId(Long processId);
}
