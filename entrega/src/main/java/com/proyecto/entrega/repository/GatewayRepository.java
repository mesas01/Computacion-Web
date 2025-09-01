package com.proyecto.entrega.repository;

import com.proyecto.entrega.entity.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatewayRepository extends JpaRepository<Gateway, Long> {
    List<Gateway> findByProcessId(Long processId);
}
