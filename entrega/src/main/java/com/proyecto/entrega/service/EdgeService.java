package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.EdgeDTO;
import com.proyecto.entrega.entity.Edge;
import com.proyecto.entrega.entity.Process;
import com.proyecto.entrega.exception.NotFoundException;
import com.proyecto.entrega.repository.ActivityRepository;
import com.proyecto.entrega.repository.EdgeRepository;
import com.proyecto.entrega.repository.GatewayRepository;
import com.proyecto.entrega.repository.ProcessRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EdgeService {

    @Autowired
    private EdgeRepository edgeRepository;
    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private GatewayRepository gatewayRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * HU-11: Crear un arco.
     */
    public EdgeDTO createEdge(EdgeDTO edgeDTO) {
        Process process = processRepository.findById(edgeDTO.getProcessId())
                .orElseThrow(() -> new NotFoundException("Proceso no encontrado con ID: " + edgeDTO.getProcessId()));

        // Validar que los nodos de origen y destino existan
        validateSourceAndTarget(edgeDTO.getSourceType(), edgeDTO.getSourceId());
        validateSourceAndTarget(edgeDTO.getTargetType(), edgeDTO.getTargetId());

        Edge edge = modelMapper.map(edgeDTO, Edge.class);
        edge.setProcess(process);

        Edge newEdge = edgeRepository.save(edge);
        return modelMapper.map(newEdge, EdgeDTO.class);
    }

    /**
     * HU-13: Eliminar un arco.
     */
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