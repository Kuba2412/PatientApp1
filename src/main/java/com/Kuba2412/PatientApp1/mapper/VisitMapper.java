package com.Kuba2412.PatientApp1.mapper;

import com.Kuba2412.PatientApp1.dto.VisitDTO;
import com.Kuba2412.PatientApp1.model.Visit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitMapper {

    VisitDTO toVisitDTO(Visit visit);

    Visit toVisit(VisitDTO visitDTO);
}
