package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.UsuarioDTO;
import com.proyecto.entrega.entity.Empresa;
import com.proyecto.entrega.entity.Rol;
import com.proyecto.entrega.entity.Usuario;
import com.proyecto.entrega.exception.DuplicateResourceException;
import com.proyecto.entrega.exception.NotFoundException;
import com.proyecto.entrega.repository.EmpresaRepository;
import com.proyecto.entrega.repository.RolRepository;
import com.proyecto.entrega.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PasswordEncoder passwordEncoder; // aqui se inyecta el  codificador de contraseñas
    @Autowired
    private ModelMapper modelMapper; // y aqui el mapeador

    /**
     * HU-02: Registro de usuario en empresa
     */
    public UsuarioDTO registrarUsuario(UsuarioDTO.Create usuarioDTO) {
        // Valida que el correo no exista, lanzando una excepción personalizada
        if (usuarioRepository.findByCorreo(usuarioDTO.getCorreo()).isPresent()) {
            throw new DuplicateResourceException("El correo ya está en uso: " + usuarioDTO.getCorreo());
        }

        // Busca las entidades relacionadas, lanzando excepciones personalizadas si no existen
        Empresa empresa = empresaRepository.findById(usuarioDTO.getEmpresaId())
                .orElseThrow(() -> new NotFoundException("No se encontró la empresa con ID: " + usuarioDTO.getEmpresaId()));
        Rol rol = rolRepository.findById(usuarioDTO.getRolId())
                .orElseThrow(() -> new NotFoundException("No se encontró el rol con ID: " + usuarioDTO.getRolId()));

        Usuario usuario = new Usuario();
        usuario.setCorreo(usuarioDTO.getCorreo());

        // le hace hash a la contraseña antes de guardarla
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        usuario.setEmpresa(empresa);
        usuario.setRol(rol);

        Usuario nuevoUsuario = usuarioRepository.save(usuario);

        // Usa ModelMapper para convertir la entidad guardada a DTO para la respuesta
        return modelMapper.map(nuevoUsuario, UsuarioDTO.class);
    }
}