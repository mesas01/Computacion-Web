package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.EmpresaDTO;
import com.proyecto.entrega.entity.Empresa;
import com.proyecto.entrega.entity.Rol;
import com.proyecto.entrega.entity.Usuario;
import com.proyecto.entrega.exception.DuplicateResourceException;
import com.proyecto.entrega.repository.EmpresaRepository;
import com.proyecto.entrega.repository.RolRepository;
import com.proyecto.entrega.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Transactional
    public EmpresaDTO registrarEmpresa(EmpresaDTO empresaDTO) {
        if (empresaRepository.findByNit(empresaDTO.getNit()).isPresent()) {
            throw new DuplicateResourceException("Ya existe una empresa con el NIT: " + empresaDTO.getNit());
        }
        if (empresaRepository.findByNombre(empresaDTO.getNombre()).isPresent()) {
            throw new DuplicateResourceException("Ya existe una empresa con el nombre: " + empresaDTO.getNombre());
        }

        Empresa nuevaEmpresa = empresaRepository.save(modelMapper.map(empresaDTO, Empresa.class));

        Rol adminRol = new Rol();
        adminRol.setNombre("Administrador");
        adminRol.setDescripcion("Rol con todos los permisos de gestión.");
        adminRol.setEmpresa(nuevaEmpresa);
        Rol rolGuardado = rolRepository.save(adminRol);

        Usuario adminUsuario = new Usuario();
        adminUsuario.setCorreo(nuevaEmpresa.getCorreoContacto());
        adminUsuario.setPassword(passwordEncoder.encode(nuevaEmpresa.getNit()));
        adminUsuario.setEmpresa(nuevaEmpresa);
        adminUsuario.setRol(rolGuardado);
        usuarioRepository.save(adminUsuario);

        return modelMapper.map(nuevaEmpresa, EmpresaDTO.class);
    }

    public List<EmpresaDTO> getAllEmpresas() {
        return empresaRepository.findAll().stream()
                .map(empresa -> modelMapper.map(empresa, EmpresaDTO.class))
                .collect(Collectors.toList());
    }
}