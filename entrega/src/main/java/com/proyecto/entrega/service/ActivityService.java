package com.proyecto.entrega.service;

import com.proyecto.entrega.dto.ActivityDTO;
import com.proyecto.entrega.entity.Activity;
import com.proyecto.entrega.entity.Process;
import com.proyecto.entrega.entity.Rol;
import com.proyecto.entrega.repository.ActivityRepository;
import com.proyecto.entrega.repository.ProcessRepository;
import com.proyecto.entrega.repository.RolRepository;
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

    /**
     * HU-08: Crear una actividad.
     */
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        Process process = processRepository.findById(activityDTO.getProcessId())
                .orElseThrow(() -> new IllegalArgumentException("Proceso no encontrado"));
        Rol rol = rolRepository.findById(activityDTO.getRolResponsableId())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));

        Activity activity = new Activity();
        activity.setName(activityDTO.getName());
        activity.setDescription(activityDTO.getDescription());
        activity.setTipo(activityDTO.getTipo());
        activity.setStatus("active");
        activity.setProcess(process);
        activity.setRolResponsable(rol);

        Activity newActivity = activityRepository.save(activity);
        return convertToDTO(newActivity);
    }

    /**
     * HU-09: Editar una actividad.
     */
    public ActivityDTO updateActivity(Long id, ActivityDTO activityDTO) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));
        Rol rol = rolRepository.findById(activityDTO.getRolResponsableId())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));

        activity.setName(activityDTO.getName());
        activity.setDescription(activityDTO.getDescription());
        activity.setTipo(activityDTO.getTipo());
        activity.setRolResponsable(rol);

        Activity updatedActivity = activityRepository.save(activity);
        return convertToDTO(updatedActivity);
    }

    /**
     * HU-10: Eliminar una actividad.
     */
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    public List<ActivityDTO> findActivitiesByProcess(Long processId) {
        return activityRepository.findByProcessId(processId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ActivityDTO convertToDTO(Activity activity) {
        return new ActivityDTO(
                activity.getId(),
                activity.getName(),
                activity.getDescription(),
                activity.getTipo(),
                activity.getStatus(),
                activity.getProcess().getId(),
                activity.getRolResponsable() != null ? activity.getRolResponsable().getId() : null
        );
    }
}
