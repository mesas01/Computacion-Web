package com.proyecto.entrega.controller;

import com.proyecto.entrega.dto.RolDTO;
import com.proyecto.entrega.service.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @PostMapping
    public ResponseEntity<RolDTO> crearRol(@Valid @RequestBody RolDTO rolDTO) {
        RolDTO nuevoRol = rolService.crearRol(rolDTO);
        return new ResponseEntity<>(nuevoRol, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Long id) {
        rolService.deleteRol(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<RolDTO>> getRolesByEmpresa(@PathVariable Long empresaId) {
        return ResponseEntity.ok(rolService.getRolesByEmpresa(empresaId));
    }
}