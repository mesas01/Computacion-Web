package com.proyecto.entrega.controller;

import com.proyecto.entrega.dto.EmpresaDTO;
import com.proyecto.entrega.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    /**
     * HU-01: Registrar una nueva empresa y su admin inicial.
     */
    @PostMapping("/registrar")
    public ResponseEntity<EmpresaDTO> registrarEmpresa(@Valid @RequestBody EmpresaDTO empresaDTO) {
        EmpresaDTO nuevaEmpresa = empresaService.registrarEmpresa(empresaDTO);
        return new ResponseEntity<>(nuevaEmpresa, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> getAllEmpresas() {
        return ResponseEntity.ok(empresaService.getAllEmpresas());
    }
}