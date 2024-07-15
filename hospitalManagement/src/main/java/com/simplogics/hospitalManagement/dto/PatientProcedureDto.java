package com.simplogics.hospitalManagement.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
@Setter
public class PatientProcedureDto {
    private String procedureId;
    private String patientId;
    private String date;
}
