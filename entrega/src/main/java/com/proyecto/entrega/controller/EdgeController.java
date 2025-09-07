package com.proyecto.entrega.controller;

import com.proyecto.entrega.dto.EdgeDTO;
import com.proyecto.entrega.service.EdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edges")
public class EdgeController {

    @Autowired
    private EdgeService edgeService;

    @PostMapping
    public ResponseEntity<EdgeDTO> createEdge(@RequestBody EdgeDTO edgeDTO) {
        EdgeDTO newEdge = edgeService.createEdge(edgeDTO);
        return new ResponseEntity<>(newEdge, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEdge(@PathVariable Long id) {
        edgeService.deleteEdge(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/process/{processId}")
    public ResponseEntity<List<EdgeDTO>> getEdgesByProcess(@PathVariable Long processId) {
        List<EdgeDTO> edges = edgeService.findEdgesByProcess(processId);
        return new ResponseEntity<>(edges, HttpStatus.OK);
    }
}
