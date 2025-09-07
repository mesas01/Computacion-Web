package com.proyecto.entrega.controller;

import com.proyecto.entrega.dto.EmpresaDTO;
import com.proyecto.entrega.service.EmpresaService;
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
     * Endpoint para HU-01: Registrar una nueva empresa.
     * @param empresaDTO Los datos de la empresa a registrar.
     * @return La empresa registrada con código 201 (Created).
     */
    @PostMapping("/registrar")
    public ResponseEntity<EmpresaDTO> registrarEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        try {
            EmpresaDTO nuevaEmpresa = empresaService.registrarEmpresa(empresaDTO);
            return new ResponseEntity<>(nuevaEmpresa, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT); // 409 Conflict si ya existe
        }
    }

    /**
     * Endpoint para obtener todas las empresas.
     * @return Lista de todas las empresas con código 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> getAllEmpresas() {
        List<EmpresaDTO> empresas = empresaService.getAllEmpresas();
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    /**
     * Endpoint para obtener una empresa por su ID.
     * @param id El ID de la empresa.
     * @return La empresa encontrada con código 200 (OK) o 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> getEmpresaById(@PathVariable Long id) {
        return empresaService.getEmpresaById(id)
                .map(empresaDTO -> new ResponseEntity<>(empresaDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
