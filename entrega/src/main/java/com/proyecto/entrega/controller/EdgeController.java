package com.proyecto.entrega.controller;

import com.proyecto.entrega.dto.EdgeDTO;
import com.proyecto.entrega.service.EdgeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edges")
@RequiredArgsConstructor
public class EdgeController {

    private final EdgeService edgeService;

    @PostMapping
    public ResponseEntity<EdgeDTO> createEdge(@Valid @RequestBody EdgeDTO edgeDTO) {
        EdgeDTO newEdge = edgeService.createEdge(edgeDTO);
        return new ResponseEntity<>(newEdge, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEdge(@PathVariable Long id) {
        edgeService.deleteEdge(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/process/{processId}")
    public ResponseEntity<List<EdgeDTO>> getEdgesByProcess(@PathVariable Long processId) {
        return ResponseEntity.ok(edgeService.findEdgesByProcess(processId));
    }
}