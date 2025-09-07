package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.ProcessDTO;
import com.proyecto.entrega.entity.Empresa;
import com.proyecto.entrega.entity.Process;
import com.proyecto.entrega.repository.EmpresaRepository;
import com.proyecto.entrega.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessService {
    @Autowired
    private ProcessRepository processRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    /**
     * HU-04: Crear un proceso.
     */
    public ProcessDTO createProcess(ProcessDTO processDTO) {
        Empresa empresa = empresaRepository.findById(processDTO.getEmpresaId())
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada"));

        Process process = new Process();
        process.setName(processDTO.getName());
        process.setDescription(processDTO.getDescription());
        process.setCategory(processDTO.getCategory());
        process.setStatus(processDTO.getStatus()); // "borrador" o "publicado"
        process.setEmpresa(empresa);

        Process newProcess = processRepository.save(process);
        return convertToDTO(newProcess);
    }

    /**
     * HU-05: Editar un proceso.
     */
    public ProcessDTO updateProcess(Long id, ProcessDTO processDTO) {
        Process process = processRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proceso no encontrado"));

        // Aquí se podría añadir lógica de historial de cambios
        process.setName(processDTO.getName());
        process.setDescription(processDTO.getDescription());
        process.setCategory(processDTO.getCategory());
        process.setStatus(processDTO.getStatus());

        Process updatedProcess = processRepository.save(process);
        return convertToDTO(updatedProcess);
    }

    /**
     * HU-06: Eliminar un proceso (borrado lógico).
     */
    public void deleteProcess(Long id) {
        processRepository.deleteById(id); // Esto ejecutará el @SQLDelete
    }

    /**
     * HU-07: Consultar procesos de una empresa.
     */
    public List<ProcessDTO> findProcessesByEmpresa(Long empresaId) {
        return processRepository.findByEmpresaId(empresaId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProcessDTO findProcessById(Long id) {
        return processRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    private ProcessDTO convertToDTO(Process process) {
        return new ProcessDTO(
                process.getId(),
                process.getName(),
                process.getDescription(),
                process.getCategory(),
                process.getStatus(),
                process.getEmpresa().getId()
        );
    }
}
