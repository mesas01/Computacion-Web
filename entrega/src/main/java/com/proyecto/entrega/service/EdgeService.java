package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.EdgeDTO;
import com.proyecto.entrega.entity.Edge;
import com.proyecto.entrega.entity.Process;
import com.proyecto.entrega.exception.NotFoundException;
import com.proyecto.entrega.repository.ActivityRepository;
import com.proyecto.entrega.repository.EdgeRepository;
import com.proyecto.entrega.repository.GatewayRepository;
import com.proyecto.entrega.repository.ProcessRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EdgeService {

    private final EdgeRepository edgeRepository;
    private final ProcessRepository processRepository;
    private final ActivityRepository activityRepository;
    private final GatewayRepository gatewayRepository;
    private final ModelMapper modelMapper;

    public EdgeDTO createEdge(EdgeDTO edgeDTO) {
        Process process = processRepository.findById(edgeDTO.getProcessId())
                .orElseThrow(() -> new NotFoundException("Proceso no encontrado con ID: " + edgeDTO.getProcessId()));

        validateSourceAndTarget(edgeDTO.getSourceType(), edgeDTO.getSourceId());
        validateSourceAndTarget(edgeDTO.getTargetType(), edgeDTO.getTargetId());

        Edge edge = modelMapper.map(edgeDTO, Edge.class);
        edge.setProcess(process);

        Edge newEdge = edgeRepository.save(edge);
        return modelMapper.map(newEdge, EdgeDTO.class);
    }

    public void deleteEdge(Long id) {
        if (!edgeRepository.existsById(id)) {
            throw new NotFoundException("Arco (Edge) no encontrado con ID: " + id);
        }
        edgeRepository.deleteById(id);
    }

    public List<EdgeDTO> findEdgesByProcess(Long processId) {
        if (!processRepository.existsById(processId)) {
            throw new NotFoundException("Proceso no encontrado con ID: " + processId);
        }
        return edgeRepository.findByProcessId(processId).stream()
                .map(edge -> modelMapper.map(edge, EdgeDTO.class))
                .collect(Collectors.toList());
    }

    private void validateSourceAndTarget(String type, Long id) {
        if ("activity".equalsIgnoreCase(type)) {
            if (!activityRepository.existsById(id)) {
                throw new NotFoundException("Actividad de origen/destino no encontrada con ID: " + id);
            }
        } else if ("gateway".equalsIgnoreCase(type)) {
            if (!gatewayRepository.existsById(id)) {
                throw new NotFoundException("Gateway de origen/destino no encontrado con ID: " + id);
            }
        }
    }
}