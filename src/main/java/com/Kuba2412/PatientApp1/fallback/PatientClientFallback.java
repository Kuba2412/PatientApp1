package com.Kuba2412.PatientApp1.fallback;

import com.Kuba2412.PatientApp1.client.PatientClient;
import com.Kuba2412.PatientApp1.dto.VisitDTO;
import com.Kuba2412.PatientApp1.handler.exception.VisitNotFound;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PatientClientFallback implements PatientClient {

    @Override
    public List<VisitDTO> getVisitsByPatientEmail(String email) {
        throw new VisitNotFound("Service is currently unavailable. Unable to retrieve visits for the provided email: " + email);
    }

    @Override
    public void registerPatientForVisit(Long visitId, String email) {
        throw new RuntimeException("Service is currently unavailable. Unable to register for visit with ID: " + visitId);
    }

    @Override
    public List<VisitDTO> getVisitsByDoctorId(Long doctorId) {
        throw new VisitNotFound("Service is currently unavailable. Unable to retrieve visits for the doctor with ID: " + doctorId);
    }

    @Override
    public List<VisitDTO> getAvailableVisitsByDoctorSpecializationAndByDate(String specialization, LocalDate date) {
        throw new VisitNotFound("Service is currently unavailable. No available dates found for specialization: " + specialization + " on date: " + date);
    }
}