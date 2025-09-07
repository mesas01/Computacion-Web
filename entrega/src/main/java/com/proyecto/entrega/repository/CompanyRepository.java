package com.proyecto.entrega.repository;

import com.proyecto.entrega.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByNit(String Nit);
    boolean existsByContactEmail(String email);
}