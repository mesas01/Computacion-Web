package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.RolDTO;
import com.proyecto.entrega.entity.Empresa;
import com.proyecto.entrega.entity.Rol;
import com.proyecto.entrega.exception.DuplicateResourceException;
import com.proyecto.entrega.exception.NotFoundException;
import com.proyecto.entrega.repository.EmpresaRepository;
import com.proyecto.entrega.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;
    private final EmpresaRepository empresaRepository;
    private final ModelMapper modelMapper;

    public RolDTO crearRol(RolDTO rolDTO) {
        Empresa empresa = empresaRepository.findById(rolDTO.getEmpresaId())
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada con ID: " + rolDTO.getEmpresaId()));

        Rol rol = modelMapper.map(rolDTO, Rol.class);
        rol.setEmpresa(empresa);

        Rol nuevoRol = rolRepository.save(rol);
        return modelMapper.map(nuevoRol, RolDTO.class);
    }

    public void deleteRol(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rol no encontrado con ID: " + id));

        if (rol.getActivities() != null && !rol.getActivities().isEmpty()) {
            throw new DuplicateResourceException("Conflicto: El rol no se puede eliminar porque está asignado a " + rol.getActivities().size() + " actividad(es).");
        }
        rolRepository.delete(rol);
    }

    public List<RolDTO> getRolesByEmpresa(Long empresaId) {
        if (!empresaRepository.existsById(empresaId)) {
            throw new NotFoundException("Empresa no encontrada con ID: " + empresaId);
        }
        return rolRepository.findByEmpresaId(empresaId).stream()
                .map(rol -> modelMapper.map(rol, RolDTO.class))
                .collect(Collectors.toList());
    }
}