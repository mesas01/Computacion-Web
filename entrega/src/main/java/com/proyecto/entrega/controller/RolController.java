package com.proyecto.entrega.controller;

import com.proyecto.entrega.dto.RolDTO;
import com.proyecto.entrega.service.RolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    /**
     * HU-17: Crear un nuevo rol.
     */
    @PostMapping
    public ResponseEntity<RolDTO> crearRol(@Valid @RequestBody RolDTO rolDTO) {
        RolDTO nuevoRol = rolService.crearRol(rolDTO);
        return new ResponseEntity<>(nuevoRol, HttpStatus.CREATED);
    }

    /**
     * HU-19: Eliminar un rol.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Long id) {
        rolService.deleteRol(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * HU-20: Consultar roles de una empresa.
     */
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<RolDTO>> getRolesByEmpresa(@PathVariable Long empresaId) {
        return ResponseEntity.ok(rolService.getRolesByEmpresa(empresaId));
    }
}