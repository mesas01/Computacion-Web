package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.CompanyDTO;
import com.proyecto.entrega.entity.Company;
import com.proyecto.entrega.repository.CompanyRepository;
import com.proyecto.entrega.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CompanyDTO getCompany(Long id) {
        Company company = companyRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Empresa no encontrada"));
        return modelMapper.map(company, CompanyDTO.class);
    }

    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll().stream()
            .map(company -> modelMapper.map(company, CompanyDTO.class))
            .collect(Collectors.toList());
    }

    @Transactional
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        Company company = modelMapper.map(companyDTO, Company.class);
        company.setActive(true); // Por defecto activa
        company = companyRepository.save(company);
        return modelMapper.map(company, CompanyDTO.class);
    }

    @Transactional
    public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) {
        Company company = companyRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Empresa no encontrada"));

        company.setName(companyDTO.getName());
        company.setAddress(companyDTO.getAddress());
        // actualizar segun cambie el DTO

        company = companyRepository.save(company);
        return modelMapper.map(company, CompanyDTO.class);
    }

    @Transactional
    public void deactivateCompany(Long id) {
        Company company = companyRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Empresa no encontrada"));
        company.setActive(false);
        companyRepository.save(company);
    }

    @Transactional
    public void deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new NotFoundException("Empresa no encontrada");
        }
        companyRepository.deleteById(id);
    }
}