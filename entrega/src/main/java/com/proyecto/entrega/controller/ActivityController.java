package com.proyecto.entrega.controller;

import com.proyecto.entrega.dto.ActivityDTO;
import com.proyecto.entrega.service.ActivityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * HU-08: Crear una actividad.
     */
    @PostMapping
    public ResponseEntity<ActivityDTO> createActivity(@Valid @RequestBody ActivityDTO activityDTO) {
        ActivityDTO newActivity = activityService.createActivity(activityDTO);
        return new ResponseEntity<>(newActivity, HttpStatus.CREATED);
    }

    /**
     * HU-09: Editar una actividad.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable Long id, @Valid @RequestBody ActivityDTO activityDTO) {
        ActivityDTO updatedActivity = activityService.updateActivity(id, activityDTO);
        return ResponseEntity.ok(updatedActivity);
    }

    /**
     * HU-10: Eliminar una actividad.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/process/{processId}")
    public ResponseEntity<List<ActivityDTO>> getActivitiesByProcess(@PathVariable Long processId) {
        return ResponseEntity.ok(activityService.findActivitiesByProcess(processId));
    }
}