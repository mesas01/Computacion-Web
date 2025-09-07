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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * HU-01: Registro de empresa con creación de admin inicial.
     * La anotación @Transactional asegura que si algo falla, toda la operación se deshace.
     */
    @Transactional
    public EmpresaDTO registrarEmpresa(EmpresaDTO empresaDTO) {
        // 1. Validar que la empresa no exista
        if (empresaRepository.findByNit(empresaDTO.getNit()).isPresent()) {
            throw new DuplicateResourceException("Ya existe una empresa con el NIT: " + empresaDTO.getNit());
        }
        if (empresaRepository.findByNombre(empresaDTO.getNombre()).isPresent()) {
            throw new DuplicateResourceException("Ya existe una empresa con el nombre: " + empresaDTO.getNombre());
        }

        // 2. Guardar la nueva empresa
        Empresa nuevaEmpresa = empresaRepository.save(modelMapper.map(empresaDTO, Empresa.class));

        // 3. Crear el rol de "Administrador" para esta empresa
        Rol adminRol = new Rol();
        adminRol.setNombre("Administrador");
        adminRol.setDescripcion("Rol con todos los permisos de gestión.");
        adminRol.setEmpresa(nuevaEmpresa);
        Rol rolGuardado = rolRepository.save(adminRol);

        // 4. Crear el usuario administrador inicial
        Usuario adminUsuario = new Usuario();
        adminUsuario.setCorreo(nuevaEmpresa.getCorreoContacto()); // Usa el correo de contacto de la empresa
        adminUsuario.setPassword(passwordEncoder.encode(nuevaEmpresa.getNit())); // Usamos el NIT como contraseña inicial segura
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