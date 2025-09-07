package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.ProcessDTO;
import com.proyecto.entrega.entity.Empresa;
import com.proyecto.entrega.entity.Process;
import com.proyecto.entrega.exception.NotFoundException;
import com.proyecto.entrega.repository.EmpresaRepository;
import com.proyecto.entrega.repository.ProcessRepository;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper modelMapper;

    /**
     * HU-04: Crear un proceso.
     */
    public ProcessDTO createProcess(ProcessDTO processDTO) {
        Empresa empresa = empresaRepository.findById(processDTO.getEmpresaId())
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada con ID: " + processDTO.getEmpresaId()));

        Process process = modelMapper.map(processDTO, Process.class);
        process.setEmpresa(empresa);

        // Asignar un estado por defecto si no se proporciona uno
        if (process.getStatus() == null || process.getStatus().trim().isEmpty()) {
            process.setStatus("borrador");
        }

        Process newProcess = processRepository.save(process);
        return modelMapper.map(newProcess, ProcessDTO.class);
    }

    /**
     * HU-05: Editar un proceso.
     */
    public ProcessDTO updateProcess(Long id, ProcessDTO processDTO) {
        Process process = processRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proceso no encontrado con ID: " + id));

        // Mapea los campos del DTO al objeto entidad existente
        modelMapper.map(processDTO, process);
        // Aseguramos que el ID no se cambie y la empresa siga siendo la misma
        process.setId(id);

        Process updatedProcess = processRepository.save(process);
        return modelMapper.map(updatedProcess, ProcessDTO.class);
    }

    /**
     * HU-06: Eliminar un proceso (borrado lógico).
     */
    public void deleteProcess(Long id) {
        if (!processRepository.existsById(id)) {
            throw new NotFoundException("Proceso no encontrado con ID: " + id);
        }
        // El @SQLDelete en la entidad se encarga de cambiar el estado a 'inactivo'
        processRepository.deleteById(id);
    }

    /**
     * HU-07: Consultar procesos de una empresa.
     */
    public List<ProcessDTO> findProcessesByEmpresa(Long empresaId) {
        if (!empresaRepository.existsById(empresaId)) {
            throw new NotFoundException("Empresa no encontrada con ID: " + empresaId);
        }

        return processRepository.findByEmpresaId(empresaId).stream()
                .map(process -> modelMapper.map(process, ProcessDTO.class))
                .collect(Collectors.toList());
    }

    public ProcessDTO findProcessById(Long id) {
        Process process = processRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proceso no encontrado con ID: " + id));
        return modelMapper.map(process, ProcessDTO.class);
    }
}