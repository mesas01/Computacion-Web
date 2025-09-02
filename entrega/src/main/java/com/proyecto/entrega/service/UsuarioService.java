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
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


//no se usa el autowired mas
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UsuarioDTO registrarUsuario(UsuarioDTO.Create usuarioDTO) {
        if (usuarioRepository.findByCorreo(usuarioDTO.getCorreo()).isPresent()) {
            throw new DuplicateResourceException("El correo ya está en uso: " + usuarioDTO.getCorreo());
        }

        Empresa empresa = empresaRepository.findById(usuarioDTO.getEmpresaId())
                .orElseThrow(() -> new NotFoundException("No se encontró la empresa con ID: " + usuarioDTO.getEmpresaId()));
        Rol rol = rolRepository.findById(usuarioDTO.getRolId())
                .orElseThrow(() -> new NotFoundException("No se encontró el rol con ID: " + usuarioDTO.getRolId()));

        Usuario usuario = new Usuario();
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setEmpresa(empresa);
        usuario.setRol(rol);

        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return modelMapper.map(nuevoUsuario, UsuarioDTO.class);
    }
}