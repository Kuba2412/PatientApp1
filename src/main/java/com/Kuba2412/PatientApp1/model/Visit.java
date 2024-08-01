package com.Kuba2412.PatientApp1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    private Long id;
    private LocalDateTime startVisit;
    private LocalDateTime endVisit;
    private Long patientId;
    private Long doctorId;
    private String specialization;
}
