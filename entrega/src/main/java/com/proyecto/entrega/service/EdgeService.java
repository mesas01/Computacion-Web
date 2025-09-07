package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.EdgeDTO;
import com.proyecto.entrega.entity.Edge;
import com.proyecto.entrega.entity.Process;
import com.proyecto.entrega.repository.ActivityRepository;
import com.proyecto.entrega.repository.EdgeRepository;
import com.proyecto.entrega.repository.GatewayRepository;
import com.proyecto.entrega.repository.ProcessRepository;
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

    /**
     * HU-11: Crear un arco.
     */
    public EdgeDTO createEdge(EdgeDTO edgeDTO) {
        Process process = processRepository.findById(edgeDTO.getProcessId())
                .orElseThrow(() -> new IllegalArgumentException("Proceso no encontrado"));

        // Validar que el origen y destino existan
        validateSourceAndTarget(edgeDTO.getSourceType(), edgeDTO.getSourceId());
        validateSourceAndTarget(edgeDTO.getTargetType(), edgeDTO.getTargetId());

        Edge edge = new Edge();
        edge.setSourceId(edgeDTO.getSourceId());
        edge.setSourceType(edgeDTO.getSourceType());
        edge.setTargetId(edgeDTO.getTargetId());
        edge.setTargetType(edgeDTO.getTargetType());
        edge.setProcess(process);

        Edge newEdge = edgeRepository.save(edge);
        return convertToDTO(newEdge);
    }

    /**
     * HU-13: Eliminar un arco.
     */
    public void deleteEdge(Long id) {
        edgeRepository.deleteById(id);
    }

    public List<EdgeDTO> findEdgesByProcess(Long processId) {
        return edgeRepository.findByProcessId(processId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private void validateSourceAndTarget(String type, Long id) {
        if ("activity".equalsIgnoreCase(type)) {
            activityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Actividad origen/destino no encontrada"));
        } else if ("gateway".equalsIgnoreCase(type)) {
            gatewayRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Gateway origen/destino no encontrado"));
        } else {
            throw new IllegalArgumentException("Tipo de origen/destino no válido: " + type);
        }
    }

    private EdgeDTO convertToDTO(Edge edge) {
        return new EdgeDTO(
                edge.getId(),
                edge.getSourceId(),
                edge.getTargetId(),
                edge.getSourceType(),
                edge.getTargetType(),
                edge.getProcess().getId()
        );
    }
}
