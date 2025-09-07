package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.GatewayDTO;
import com.proyecto.entrega.entity.Gateway;
import com.proyecto.entrega.entity.Process;
import com.proyecto.entrega.repository.GatewayRepository;
import com.proyecto.entrega.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GatewayService {

    @Autowired
    private GatewayRepository gatewayRepository;

    @Autowired
    private ProcessRepository processRepository;

    /**
     * HU-14: Crear un gateway.
     */
    public GatewayDTO createGateway(GatewayDTO gatewayDTO) {
        Process process = processRepository.findById(gatewayDTO.getProcessId())
                .orElseThrow(() -> new IllegalArgumentException("Proceso no encontrado"));

        Gateway gateway = new Gateway();
        gateway.setTipo(gatewayDTO.getTipo());
        gateway.setProcess(process);

        Gateway newGateway = gatewayRepository.save(gateway);
        return convertToDTO(newGateway);
    }

    public List<GatewayDTO> getGatewaysByProcess(Long processId) {
        return gatewayRepository.findByProcessId(processId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private GatewayDTO convertToDTO(Gateway gateway) {
        return new GatewayDTO(
                gateway.getId(),
                gateway.getTipo(),
                gateway.getProcess().getId()
        );
    }
}
