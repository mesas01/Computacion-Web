package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.GatewayDTO;
import com.proyecto.entrega.entity.Gateway;
import com.proyecto.entrega.entity.Process;
import com.proyecto.entrega.exception.NotFoundException;
import com.proyecto.entrega.repository.GatewayRepository;
import com.proyecto.entrega.repository.ProcessRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GatewayService {

    private final GatewayRepository gatewayRepository;
    private final ProcessRepository processRepository;
    private final ModelMapper modelMapper;

    public GatewayDTO createGateway(GatewayDTO gatewayDTO) {
        Process process = processRepository.findById(gatewayDTO.getProcessId())
                .orElseThrow(() -> new NotFoundException("Proceso no encontrado con ID: " + gatewayDTO.getProcessId()));

        Gateway gateway = modelMapper.map(gatewayDTO, Gateway.class);
        gateway.setProcess(process);

        Gateway newGateway = gatewayRepository.save(gateway);
        return modelMapper.map(newGateway, GatewayDTO.class);
    }

    public void deleteGateway(Long id) {
        if (!gatewayRepository.existsById(id)) {
            throw new NotFoundException("Gateway no encontrado con ID: " + id);
        }
        gatewayRepository.deleteById(id);
    }

    public List<GatewayDTO> getGatewaysByProcess(Long processId) {
        if (!processRepository.existsById(processId)) {
            throw new NotFoundException("Proceso no encontrado con ID: " + processId);
        }
        return gatewayRepository.findByProcessId(processId).stream()
                .map(gateway -> modelMapper.map(gateway, GatewayDTO.class))
                .collect(Collectors.toList());
    }
}