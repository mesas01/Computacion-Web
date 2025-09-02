package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.ProcessDTO;
import com.proyecto.entrega.entity.Empresa;
import com.proyecto.entrega.entity.Process;
import com.proyecto.entrega.exception.NotFoundException;
import com.proyecto.entrega.repository.EmpresaRepository;
import com.proyecto.entrega.repository.ProcessRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcessService {

    private final ProcessRepository processRepository;
    private final EmpresaRepository empresaRepository;
    private final ModelMapper modelMapper;

    public ProcessDTO createProcess(ProcessDTO processDTO) {
        Empresa empresa = empresaRepository.findById(processDTO.getEmpresaId())
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada con ID: " + processDTO.getEmpresaId()));

        Process process = modelMapper.map(processDTO, Process.class);
        process.setEmpresa(empresa);

        if (process.getStatus() == null || process.getStatus().trim().isEmpty()) {
            process.setStatus("borrador");
        }

        Process newProcess = processRepository.save(process);
        return modelMapper.map(newProcess, ProcessDTO.class);
    }

    public ProcessDTO updateProcess(Long id, ProcessDTO processDTO) {
        Process process = processRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proceso no encontrado con ID: " + id));

        modelMapper.map(processDTO, process);
        process.setId(id);

        Process updatedProcess = processRepository.save(process);
        return modelMapper.map(updatedProcess, ProcessDTO.class);
    }

    public void deleteProcess(Long id) {
        if (!processRepository.existsById(id)) {
            throw new NotFoundException("Proceso no encontrado con ID: " + id);
        }
        processRepository.deleteById(id);
    }

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