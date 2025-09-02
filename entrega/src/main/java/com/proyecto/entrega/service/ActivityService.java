package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.ActivityDTO;
import com.proyecto.entrega.entity.Activity;
import com.proyecto.entrega.entity.Process;
import com.proyecto.entrega.entity.Rol;
import com.proyecto.entrega.exception.NotFoundException;
import com.proyecto.entrega.repository.ActivityRepository;
import com.proyecto.entrega.repository.ProcessRepository;
import com.proyecto.entrega.repository.RolRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * HU-08: Crear una actividad.
     */
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        Process process = processRepository.findById(activityDTO.getProcessId())
                .orElseThrow(() -> new NotFoundException("Proceso no encontrado con ID: " + activityDTO.getProcessId()));
        Rol rol = rolRepository.findById(activityDTO.getRolResponsableId())
                .orElseThrow(() -> new NotFoundException("Rol no encontrado con ID: " + activityDTO.getRolResponsableId()));

        Activity activity = modelMapper.map(activityDTO, Activity.class);
        activity.setProcess(process);
        activity.setRolResponsable(rol);
        activity.setStatus("active"); // Estado por defecto

        Activity newActivity = activityRepository.save(activity);
        return modelMapper.map(newActivity, ActivityDTO.class);
    }

    /**
     * HU-09: Editar una actividad.
     */
    public ActivityDTO updateActivity(Long id, ActivityDTO activityDTO) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Actividad no encontrada con ID: " + id));
        Rol rol = rolRepository.findById(activityDTO.getRolResponsableId())
                .orElseThrow(() -> new NotFoundException("Rol no encontrado con ID: " + activityDTO.getRolResponsableId()));

        modelMapper.map(activityDTO, activity);
        activity.setId(id); // Aseguramos que el ID no cambie
        activity.setRolResponsable(rol);

        Activity updatedActivity = activityRepository.save(activity);
        return modelMapper.map(updatedActivity, ActivityDTO.class);
    }

    /**
     * HU-10: Eliminar una actividad (borrado lógico).
     */
    public void deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new NotFoundException("Actividad no encontrada con ID: " + id);
        }
        activityRepository.deleteById(id); // El @SQLDelete en la entidad hace el trabajo
    }

    public List<ActivityDTO> findActivitiesByProcess(Long processId) {
        if (!processRepository.existsById(processId)) {
            throw new NotFoundException("Proceso no encontrado con ID: " + processId);
        }

        return activityRepository.findByProcessId(processId).stream()
                .map(activity -> modelMapper.map(activity, ActivityDTO.class))
                .collect(Collectors.toList());
    }
}