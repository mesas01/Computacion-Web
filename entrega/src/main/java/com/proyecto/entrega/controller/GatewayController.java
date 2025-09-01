package com.proyecto.entrega.controller;

import com.proyecto.entrega.dto.GatewayDTO;
import com.proyecto.entrega.service.GatewayService;
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

    @PostMapping
    public ResponseEntity<GatewayDTO> createGateway(@RequestBody GatewayDTO gatewayDTO) {
        GatewayDTO newGateway = gatewayService.createGateway(gatewayDTO);
        return new ResponseEntity<>(newGateway, HttpStatus.CREATED);
    }

    @GetMapping("/process/{processId}")
    public ResponseEntity<List<GatewayDTO>> getGatewaysByProcess(@PathVariable Long processId) {
        List<GatewayDTO> gateways = gatewayService.getGatewaysByProcess(processId);
        return new ResponseEntity<>(gateways, HttpStatus.OK);
    }
}
