package com.proyecto.entrega.controller;

import com.proyecto.entrega.dto.ProcessDTO;
import com.proyecto.entrega.service.ProcessService;
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

    @PostMapping
    public ResponseEntity<ProcessDTO> createProcess(@RequestBody ProcessDTO processDTO) {
        ProcessDTO newProcess = processService.createProcess(processDTO);
        return new ResponseEntity<>(newProcess, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcessDTO> updateProcess(@PathVariable Long id, @RequestBody ProcessDTO processDTO) {
        ProcessDTO updatedProcess = processService.updateProcess(id, processDTO);
        return new ResponseEntity<>(updatedProcess, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcess(@PathVariable Long id) {
        processService.deleteProcess(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<ProcessDTO>> getProcessesByEmpresa(@PathVariable Long empresaId) {
        List<ProcessDTO> processes = processService.findProcessesByEmpresa(empresaId);
        return new ResponseEntity<>(processes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessDTO> getProcessById(@PathVariable Long id) {
        ProcessDTO process = processService.findProcessById(id);
        if (process != null) {
            return new ResponseEntity<>(process, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
