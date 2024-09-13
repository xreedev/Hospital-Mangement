package com.simplogics.hospitalManagement.service;

import com.simplogics.hospitalManagement.advice.HospitalException;
import com.simplogics.hospitalManagement.dto.PatientDTO;
import com.simplogics.hospitalManagement.dto.PatientProcedureDto;
import com.simplogics.hospitalManagement.dto.ResponseDTO;

import java.util.InvalidPropertiesFormatException;

public interface IPatientService {
    ResponseDTO createPatient(PatientDTO patientDto) throws HospitalException;

    ResponseDTO setPatPro(PatientProcedureDto procedureDto) throws HospitalException;

    ResponseDTO updatePatient(Integer pid, String Name);

    ResponseDTO delPatient(Integer pId) throws HospitalException;

    ResponseDTO getPatients();
}
