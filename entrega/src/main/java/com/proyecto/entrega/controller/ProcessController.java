package com.proyecto.entrega.controller;

import com.proyecto.entrega.dto.ProcessDTO;
import com.proyecto.entrega.service.ProcessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processes")
@RequiredArgsConstructor
public class ProcessController {

    private final ProcessService processService;

    @PostMapping
    public ResponseEntity<ProcessDTO> createProcess(@Valid @RequestBody ProcessDTO processDTO) {
        ProcessDTO newProcess = processService.createProcess(processDTO);
        return new ResponseEntity<>(newProcess, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcessDTO> updateProcess(@PathVariable Long id, @Valid @RequestBody ProcessDTO processDTO) {
        ProcessDTO updatedProcess = processService.updateProcess(id, processDTO);
        return ResponseEntity.ok(updatedProcess);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcess(@PathVariable Long id) {
        processService.deleteProcess(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<ProcessDTO>> getProcessesByEmpresa(@PathVariable Long empresaId) {
        return ResponseEntity.ok(processService.findProcessesByEmpresa(empresaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessDTO> getProcessById(@PathVariable Long id) {
        return ResponseEntity.ok(processService.findProcessById(id));
    }
}