package com.proyecto.entrega.controller;

import com.proyecto.entrega.dto.ProcessDTO;
import com.proyecto.entrega.service.ProcessService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processes")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    /**
     * HU-04: Crear un proceso.
     */
    @PostMapping
    public ResponseEntity<ProcessDTO> createProcess(@Valid @RequestBody ProcessDTO processDTO) {
        ProcessDTO newProcess = processService.createProcess(processDTO);
        return new ResponseEntity<>(newProcess, HttpStatus.CREATED);
    }

    /**
     * HU-05: Editar un proceso.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProcessDTO> updateProcess(@PathVariable Long id, @Valid @RequestBody ProcessDTO processDTO) {
        ProcessDTO updatedProcess = processService.updateProcess(id, processDTO);
        return ResponseEntity.ok(updatedProcess);
    }

    /**
     * HU-06: Eliminar un proceso.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcess(@PathVariable Long id) {
        processService.deleteProcess(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * HU-07: Consultar procesos de una empresa.
     */
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<ProcessDTO>> getProcessesByEmpresa(@PathVariable Long empresaId) {
        return ResponseEntity.ok(processService.findProcessesByEmpresa(empresaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessDTO> getProcessById(@PathVariable Long id) {
        return ResponseEntity.ok(processService.findProcessById(id));
    }
}