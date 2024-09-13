package com.simplogics.hospitalManagement.util;

import com.simplogics.hospitalManagement.dto.PatientDTO;
import com.simplogics.hospitalManagement.dto.PatientProcedureDto;
import com.simplogics.hospitalManagement.dto.ResponseDTO;
import com.simplogics.hospitalManagement.model.Patient;
import java.util.ArrayList;
import java.util.List;

public class ToDtoGlobal {
    public static ResponseDTO listToPatientDTO(List<Patient> P){
        PatientDTO patientDto=new PatientDTO();
        List<String> names=new ArrayList<>();
        for(Patient patient:P) {
            names.add(patient.getName());
        }
        patientDto.setPatientName(names);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(patientDto);
        return responseDTO;
    }
    public static ResponseDTO toPatientProcedureDto(PatientProcedureDto P){
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setData(P);
        return responseDTO;
    }
    public static ResponseDTO toPatientDto(Patient P){
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setData(P);
        return responseDTO;
    }
}
