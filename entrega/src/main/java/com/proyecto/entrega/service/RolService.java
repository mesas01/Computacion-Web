package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.RolDTO;
import com.proyecto.entrega.entity.Empresa;
import com.proyecto.entrega.entity.Rol;
import com.proyecto.entrega.repository.EmpresaRepository;
import com.proyecto.entrega.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    /**
     * HU-17: Crear un rol de proceso.
     */
    public RolDTO crearRol(RolDTO rolDTO) {
        Empresa empresa = empresaRepository.findById(rolDTO.getEmpresaId())
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada"));

        Rol rol = new Rol();
        rol.setNombre(rolDTO.getNombre());
        rol.setDescripcion(rolDTO.getDescripcion());
        rol.setEmpresa(empresa);

        Rol nuevoRol = rolRepository.save(rol);
        return convertToDTO(nuevoRol);
    }

    /**
     * HU-20: Consultar roles de proceso.
     */
    public List<RolDTO> getRolesByEmpresa(Long empresaId) {
        return rolRepository.findByEmpresaId(empresaId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private RolDTO convertToDTO(Rol rol) {
        return new RolDTO(
                rol.getId(),
                rol.getNombre(),
                rol.getDescripcion(),
                rol.getEmpresa().getId()
        );
    }
}
