package com.Kuba2412.PatientApp1.service;

import com.Kuba2412.PatientApp1.client.PatientClient;
import com.Kuba2412.PatientApp1.dto.VisitDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PatientServiceTest {

    private PatientService patientService;

    private PatientClient patientClient;

    @BeforeEach
    void setUp() {
        patientClient = Mockito.mock(PatientClient.class);
        patientService = new PatientService(patientClient);
    }

    @Test
    void getVisitsByPatientEmail_Success() {
        // given
        String email = "test@example.com";
        List<VisitDTO> visits = List.of(new VisitDTO(1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1L, 1L, "Specialization"));
        when(patientClient.getVisitsByPatientEmail(email)).thenReturn(visits);

        // when
        List<VisitDTO> result = patientService.getVisitsByPatientEmail(email);

        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(patientClient, times(1)).getVisitsByPatientEmail(email);
    }

    @Test
    void getVisitsByPatientEmail_NotFound() {
        // given
        String email = "nonexistent@example.com";
        when(patientClient.getVisitsByPatientEmail(email)).thenReturn(Collections.emptyList());

        // when
        List<VisitDTO> result = patientService.getVisitsByPatientEmail(email);

        // then
        assertTrue(result.isEmpty());
        verify(patientClient, times(1)).getVisitsByPatientEmail(email);
    }

    @Test
    void registerPatientForVisit_Success() {
        // given
        Long visitId = 1L;
        String email = "test@example.com";

        // when
        patientService.registerPatientForVisit(visitId, email);

        // then
        verify(patientClient, times(1)).registerPatientForVisit(visitId, email);
    }

    @Test
    void registerPatientForVisit_Failure() {
        // given
        Long visitId = 1L;
        String email = "test@example.com";
        doThrow(new RuntimeException("Registration failed")).when(patientClient).registerPatientForVisit(visitId, email);

        // when + then
        assertThrows(RuntimeException.class, () -> {
            patientService.registerPatientForVisit(visitId, email);
        });
        verify(patientClient, times(1)).registerPatientForVisit(visitId, email);
    }

    @Test
    void getAvailableVisitsByDoctorId_Success() {
        // given
        Long doctorId = 1L;
        List<VisitDTO> visits = List.of(new VisitDTO(1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1L, 1L, "Specialization"));
        when(patientClient.getVisitsByDoctorId(doctorId)).thenReturn(visits);

        // when
        List<VisitDTO> result = patientService.getAvailableVisitsByDoctorId(doctorId);

        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(patientClient, times(1)).getVisitsByDoctorId(doctorId);
    }

    @Test
    void getAvailableVisitsByDoctorId_NotFound() {
        // given
        Long doctorId = 1L;
        when(patientClient.getVisitsByDoctorId(doctorId)).thenReturn(Collections.emptyList());

        // when
        List<VisitDTO> result = patientService.getAvailableVisitsByDoctorId(doctorId);

        // then
        assertTrue(result.isEmpty());
        verify(patientClient, times(1)).getVisitsByDoctorId(doctorId);
    }

    @Test
    void getAvailableVisitsByDoctorSpecializationAndByDate_Success() {
        // given
        String specialization = "Cardiology";
        LocalDate date = LocalDate.now();
        List<VisitDTO> visits = List.of(new VisitDTO(1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1L, 1L, specialization));
        when(patientClient.getAvailableVisitsByDoctorSpecializationAndByDate(specialization, date)).thenReturn(visits);

        // when
        List<VisitDTO> result = patientService.getAvailableVisitsByDoctorSpecializationAndByDate(specialization, date);

        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(patientClient, times(1)).getAvailableVisitsByDoctorSpecializationAndByDate(specialization, date);
    }

    @Test
    void getAvailableVisitsByDoctorSpecializationAndByDate_NotFound() {
        // given
        String specialization = "NonExistentSpecialization";
        LocalDate date = LocalDate.now();
        when(patientClient.getAvailableVisitsByDoctorSpecializationAndByDate(specialization, date)).thenReturn(Collections.emptyList());

        // when
        List<VisitDTO> result = patientService.getAvailableVisitsByDoctorSpecializationAndByDate(specialization, date);

        // then
        assertTrue(result.isEmpty());
        verify(patientClient, times(1)).getAvailableVisitsByDoctorSpecializationAndByDate(specialization, date);
    }
}