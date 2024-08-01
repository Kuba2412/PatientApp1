package com.Kuba2412.PatientApp1.service;

import com.Kuba2412.PatientApp1.client.PatientClient;
import com.Kuba2412.PatientApp1.dto.VisitDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientClient patientClient;

    public List<VisitDTO> getVisitsByPatientEmail(String email) {
        return patientClient.getVisitsByPatientEmail(email);
    }

    public void registerPatientForVisit(Long visitId, String email) {
        patientClient.registerPatientForVisit(visitId, email);
    }

    public List<VisitDTO> getAvailableVisitsByDoctorId(Long doctorId) {
        return patientClient.getVisitsByDoctorId(doctorId);
    }

    public List<VisitDTO> getAvailableVisitsByDoctorSpecializationAndByDate(String specialization, LocalDate date) {
        return patientClient.getAvailableVisitsByDoctorSpecializationAndByDate(specialization, date);
    }
}