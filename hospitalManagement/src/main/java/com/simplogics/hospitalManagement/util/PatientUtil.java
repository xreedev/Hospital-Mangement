package com.simplogics.hospitalManagement.util;

import com.simplogics.hospitalManagement.dto.PatientDTO;
import com.simplogics.hospitalManagement.model.Patient;
import com.simplogics.hospitalManagement.model.PatientProcedure;

import java.util.ArrayList;
import java.util.List;

public class PatientUtil {
    public static List<Patient> createPatient(PatientDTO patientDTO){
        List<Patient> patients=new ArrayList<>();
        for(String name:patientDTO.getPatientName()){
        Patient p=new Patient();
        p.setName(name);
        patients.add(p);
        }
        return patients;
    }
    public static PatientDTO createPatientDto(List<Patient> P){
        List<String> patientNames=new ArrayList<>();
        for(Patient patient:P){
            patientNames.add(patient.getName());
        }
        PatientDTO patientDTO=new PatientDTO();
        patientDTO.setPatientName(patientNames);
        return patientDTO;
    }
}
