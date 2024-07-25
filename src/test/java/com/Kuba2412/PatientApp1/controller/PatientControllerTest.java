package com.Kuba2412.PatientApp1.controller;

import com.Kuba2412.PatientApp1.client.PatientClient;
import com.Kuba2412.PatientApp1.dto.VisitDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientClient patientClient;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void getVisitsByPatientEmail_Success() throws Exception {
        String email = "test@example.com";
        List<VisitDTO> visits = List.of(new VisitDTO(1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1L, 2L, "Cardiology"));

        when(patientClient.getVisitsByPatientEmail(email)).thenReturn(visits);

        mockMvc.perform(get("/patients/{email}/visits", email))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(visits.get(0).getId()));
    }

    @Test
    void getVisitsByPatientEmail_NotFound() throws Exception {
        String email = "nonexistent@example.com";

        when(patientClient.getVisitsByPatientEmail(email)).thenThrow(new RuntimeException("Patient not found"));

        mockMvc.perform(get("/patients/{email}/visits", email))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Patient not found"));
    }

    @Test
    void registerPatientForVisit_Success() throws Exception {
        Long visitId = 1L;
        String email = "test@example.com";

        doNothing().when(patientClient).registerPatientForVisit(visitId, email);

        mockMvc.perform(post("/patients/visits/{visitId}/register", visitId)
                        .param("email", email))
                .andExpect(status().isOk());
    }

    @Test
    void getVisitsByDoctorId_Success() throws Exception {
        Long doctorId = 1L;
        List<VisitDTO> visits = List.of(new VisitDTO(1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1L, 2l, "Cardiology"));

        when(patientClient.getVisitsByDoctorId(doctorId)).thenReturn(visits);

        mockMvc.perform(get("/patients/doctors/{doctorId}/visits", doctorId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(visits.get(0).getId()));
    }

    @Test
    void getVisitsByDoctorId_DoctorNotFound() throws Exception {
        Long doctorId = 123L;

        when(patientClient.getVisitsByDoctorId(doctorId)).thenThrow(new RuntimeException("Doctor not found"));

        mockMvc.perform(get("/patients/doctors/{doctorId}/visits", doctorId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Doctor not found"));
    }

    @Test
    void getAvailableVisitsByDoctorSpecializationAndByDate_Success() throws Exception {
        String specialization = "Cardiology";
        LocalDate date = LocalDate.of(2024, 7, 24);
        List<VisitDTO> visits = List.of(new VisitDTO(1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1L, 2L, "Cardiology"));

        when(patientClient.getAvailableVisitsByDoctorSpecializationAndByDate(specialization, date)).thenReturn(visits);

        mockMvc.perform(get("/patients/doctors/specialization/{specialization}/available-dates", specialization)
                        .param("date", date.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(visits.get(0).getId()));
    }

    @Test
    void getAvailableVisitsByDoctorSpecializationAndByDate_NotFound() throws Exception {
        String specialization = "NonExistentSpecialization";
        LocalDate date = LocalDate.of(2024, 7, 24);

        when(patientClient.getAvailableVisitsByDoctorSpecializationAndByDate(specialization, date))
                .thenThrow(new RuntimeException("No available visits found for the specified criteria"));

        mockMvc.perform(get("/patients/doctors/specialization/{specialization}/available-dates", specialization)
                        .param("date", date.toString()))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No available visits found for the specified criteria"));
    }
}







