package com.proyecto.entrega.controller;

import com.proyecto.entrega.dto.ActivityDTO;
import com.proyecto.entrega.service.ActivityService;
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

    @PostMapping
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO) {
        ActivityDTO newActivity = activityService.createActivity(activityDTO);
        return new ResponseEntity<>(newActivity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable Long id, @RequestBody ActivityDTO activityDTO) {
        ActivityDTO updatedActivity = activityService.updateActivity(id, activityDTO);
        return new ResponseEntity<>(updatedActivity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/process/{processId}")
    public ResponseEntity<List<ActivityDTO>> getActivitiesByProcess(@PathVariable Long processId) {
        List<ActivityDTO> activities = activityService.findActivitiesByProcess(processId);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }
}
