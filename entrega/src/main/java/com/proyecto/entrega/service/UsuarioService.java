package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.UsuarioDTO;
import com.proyecto.entrega.entity.Empresa;
import com.proyecto.entrega.entity.Rol;
import com.proyecto.entrega.entity.Usuario;
import com.proyecto.entrega.repository.EmpresaRepository;
import com.proyecto.entrega.repository.RolRepository;
import com.proyecto.entrega.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private RolRepository rolRepository;

    /**
     * HU-02: Registro de usuario en empresa.
     */
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        Empresa empresa = empresaRepository.findById(usuarioDTO.getEmpresaId())
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada"));
        Rol rol = rolRepository.findById(usuarioDTO.getRolId())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));

        if (usuarioRepository.findByCorreo(usuarioDTO.getCorreo()).isPresent()) {
            throw new IllegalStateException("El correo ya está en uso.");
        }

        Usuario usuario = new Usuario();
        usuario.setCorreo(usuarioDTO.getCorreo());
        // En un caso real, la contraseña debería ser hasheada
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setEmpresa(empresa);
        usuario.setRol(rol);

        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return convertToDTO(nuevoUsuario);
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getCorreo(),
                usuario.getEmpresa().getId(),
                usuario.getRol().getId()
        );
    }
}
