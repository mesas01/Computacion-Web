package com.proyecto.entrega.controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.entrega.dto.ActivityDTO;
import com.proyecto.entrega.service.ActivityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ActivityController.class)
class ActivityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActivityService activityService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getActivity_returnsOkWithBody() throws Exception {
        ActivityDTO dto = new ActivityDTO(1L, "Actividad 1", 10.0, 20.0, "desc", 100.0, 50.0, "active");
        Mockito.when(activityService.findActivity(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/activity/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Actividad 1"));
    }

    @Test
    void getActivities_returnsList() throws Exception {
        List<ActivityDTO> list = List.of(
                new ActivityDTO(1L, "Act1", 1.0, 2.0, "d", 3.0, 4.0, "active"),
                new ActivityDTO(2L, "Act2", 5.0, 6.0, "d2", 7.0, 8.0, "active")
        );
        Mockito.when(activityService.findActivities()).thenReturn(list);

        mockMvc.perform(get("/api/activity"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].name").value("Act2"));
    }

    @Test
    void createActivity_returnsOk_andCallsService() throws Exception {
        ActivityDTO payload = new ActivityDTO(null, "Nueva", 1.0, 2.0, "d", 3.0, 4.0, "active");

        mockMvc.perform(post("/api/activity")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk());

        verify(activityService).createActivity(any(ActivityDTO.class));
    }

    @Test
    void updateActivity_returnsOk_andCallsService() throws Exception {
        ActivityDTO payload = new ActivityDTO(10L, "Editada", 1.0, 2.0, "d", 3.0, 4.0, "active");

        mockMvc.perform(put("/api/activity")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk());

        verify(activityService).updateActivity(any(ActivityDTO.class));
    }

    @Test
    void deleteActivity_returnsOk_andCallsService() throws Exception {
        mockMvc.perform(delete("/api/activity/5"))
                .andExpect(status().isOk());

        verify(activityService).deleteActivity(5L);
    }
}
