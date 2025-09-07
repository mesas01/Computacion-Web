package com.proyecto.entrega.controller;

import com.proyecto.entrega.dto.RolDTO;
import com.proyecto.entrega.service.RolService;
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

    @PostMapping
    public ResponseEntity<RolDTO> crearRol(@RequestBody RolDTO rolDTO) {
        RolDTO nuevoRol = rolService.crearRol(rolDTO);
        return new ResponseEntity<>(nuevoRol, HttpStatus.CREATED);
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<RolDTO>> getRolesByEmpresa(@PathVariable Long empresaId) {
        List<RolDTO> roles = rolService.getRolesByEmpresa(empresaId);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
