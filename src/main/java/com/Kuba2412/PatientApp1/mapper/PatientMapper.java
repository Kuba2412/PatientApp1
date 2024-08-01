package com.Kuba2412.PatientApp1.mapper;

import com.Kuba2412.PatientApp1.dto.PatientDTO;
import com.Kuba2412.PatientApp1.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDTO toPatientDTO(Patient patient);

    Patient toPatient(PatientDTO patientDTO);
}
