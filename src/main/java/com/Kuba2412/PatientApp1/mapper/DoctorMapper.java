package com.Kuba2412.PatientApp1.mapper;

import com.Kuba2412.PatientApp1.dto.DoctorDTO;
import com.Kuba2412.PatientApp1.model.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorDTO toDoctorDTO(Doctor doctor);

    Doctor toDoctor(DoctorDTO doctorDTO);
}
