package com.simplogics.hospitalManagement.service;

import com.simplogics.hospitalManagement.advice.FieldRequiredException;
import com.simplogics.hospitalManagement.advice.InvalidDataFormatException;
import com.simplogics.hospitalManagement.advice.NoDependencyException;
import com.simplogics.hospitalManagement.advice.NullRequestException;
import com.simplogics.hospitalManagement.dto.PatientDTO;
import com.simplogics.hospitalManagement.dto.PatientProcedureDto;
import com.simplogics.hospitalManagement.dto.ResponseDTO;

import java.util.InvalidPropertiesFormatException;

public interface IPatientService {
    ResponseDTO createPatient(PatientDTO patientDto) throws FieldRequiredException;

    ResponseDTO setPatPro(PatientProcedureDto procedureDto) throws FieldRequiredException, NoDependencyException, InvalidDataFormatException, InvalidPropertiesFormatException;

    ResponseDTO updatePatient(Integer pid, String Name);

    ResponseDTO delPatient(Integer pId) throws NullRequestException;

    ResponseDTO getPatients();
}
