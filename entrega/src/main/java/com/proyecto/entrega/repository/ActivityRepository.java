package com.proyecto.entrega.repository;

import com.proyecto.entrega.entity.Activity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByProcessId(Long processId);
}
