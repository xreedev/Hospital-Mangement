package com.simplogics.hospitalManagement.util;

import com.simplogics.hospitalManagement.dto.PatientDTO;
import com.simplogics.hospitalManagement.model.Patient;

public class ToEntity {
    public static Patient dtoToPatient(PatientDTO patientDTO){
        Patient P=new Patient();
        P.setName(patientDTO.getPatientName().getFirst());
        return P;
    }
}
