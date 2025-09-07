package com.proyecto.entrega.controller;

import com.proyecto.entrega.dto.GatewayDTO;
import com.proyecto.entrega.service.GatewayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gateways")
public class GatewayController {

    @Autowired
    private GatewayService gatewayService;

    /**
     * HU-14: Crear un gateway.
     */
    @PostMapping
    public ResponseEntity<GatewayDTO> createGateway(@Valid @RequestBody GatewayDTO gatewayDTO) {
        GatewayDTO newGateway = gatewayService.createGateway(gatewayDTO);
        return new ResponseEntity<>(newGateway, HttpStatus.CREATED);
    }

    /**
     * HU-16: Eliminar un gateway.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGateway(@PathVariable Long id) {
        gatewayService.deleteGateway(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/process/{processId}")
    public ResponseEntity<List<GatewayDTO>> getGatewaysByProcess(@PathVariable Long processId) {
        return ResponseEntity.ok(gatewayService.getGatewaysByProcess(processId));
    }
}