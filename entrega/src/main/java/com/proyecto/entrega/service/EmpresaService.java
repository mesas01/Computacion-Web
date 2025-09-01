package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.EmpresaDTO;
import com.proyecto.entrega.entity.Empresa;
import com.proyecto.entrega.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    /**
     * HU-01: Registro de empresa.
     * Crea una nueva empresa en el sistema.
     * @param empresaDTO Datos de la empresa a crear.
     * @return El DTO de la empresa creada.
     * @throws IllegalStateException si ya existe una empresa con el mismo NIT o nombre.
     */
    public EmpresaDTO registrarEmpresa(EmpresaDTO empresaDTO) {
        // Validar que no exista una empresa con el mismo NIT o nombre
        if (empresaRepository.findByNit(empresaDTO.getNit()).isPresent()) {
            throw new IllegalStateException("Ya existe una empresa con el NIT proporcionado.");
        }
        if (empresaRepository.findByNombre(empresaDTO.getNombre()).isPresent()) {
            throw new IllegalStateException("Ya existe una empresa con el nombre proporcionado.");
        }

        Empresa empresa = new Empresa();
        empresa.setNombre(empresaDTO.getNombre());
        empresa.setNit(empresaDTO.getNit());
        empresa.setCorreoContacto(empresaDTO.getCorreoContacto());

        Empresa nuevaEmpresa = empresaRepository.save(empresa);

        // Aquí se podría añadir la lógica para crear el usuario administrador inicial.

        return convertToDTO(nuevaEmpresa);
    }

    /**
     * Obtiene una empresa por su ID.
     * @param id El ID de la empresa.
     * @return Un Optional con el DTO de la empresa si se encuentra.
     */
    public Optional<EmpresaDTO> getEmpresaById(Long id) {
        return empresaRepository.findById(id).map(this::convertToDTO);
    }

    /**
     * Obtiene todas las empresas registradas.
     * @return Lista de DTOs de todas las empresas.
     */
    public List<EmpresaDTO> getAllEmpresas() {
        return empresaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // --- Métodos de conversión ---

    private EmpresaDTO convertToDTO(Empresa empresa) {
        return new EmpresaDTO(
                empresa.getId(),
                empresa.getNombre(),
                empresa.getNit(),
                empresa.getCorreoContacto()
        );
    }
}
