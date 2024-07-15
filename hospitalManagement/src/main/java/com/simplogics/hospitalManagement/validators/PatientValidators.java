package com.simplogics.hospitalManagement.validators;

import com.simplogics.hospitalManagement.advice.FieldRequiredException;
import com.simplogics.hospitalManagement.advice.InvalidDataFormatException;
import com.simplogics.hospitalManagement.advice.NoDependencyException;
import com.simplogics.hospitalManagement.constants.ExceptionConstants;
import com.simplogics.hospitalManagement.dto.PatientDTO;
import com.simplogics.hospitalManagement.dto.PatientProcedureDto;
import com.simplogics.hospitalManagement.repository.IPatientRepository;
import com.simplogics.hospitalManagement.repository.IProcedureRepository;
import com.simplogics.hospitalManagement.util.Parser;

import java.util.Date;
import java.util.InvalidPropertiesFormatException;

public class PatientValidators {
    public static void patientDtoCheck(PatientDTO patientDTO) throws FieldRequiredException {
        if(patientDTO.getPatientName()==null){
            throw new FieldRequiredException(ExceptionConstants.PATIENT_NAME_REQUIRED);
        }
    }
    public static void patientProcedureDtoCheck(PatientProcedureDto pdto, IPatientRepository patientRepository, IProcedureRepository procedureRepository) throws FieldRequiredException, NoDependencyException, InvalidDataFormatException, InvalidPropertiesFormatException {
        if(pdto.getPatientId()==null || pdto.getProcedureId()==null || pdto.getDate()==null){
            throw new FieldRequiredException(ExceptionConstants.FIELD_CANNOT_BE_NULL);
        }
        Parser.dateParser(pdto.getDate());
        Parser.intParser(pdto.getPatientId());
        Parser.intParser(pdto.getProcedureId());
        Long patId=Long.parseLong(pdto.getPatientId());
        Long proId=Long.parseLong(pdto.getProcedureId());
        if(patientRepository.findById(patId).isEmpty()){
            throw new NoDependencyException(ExceptionConstants.PATIENT_DOES_NOT_EXIST);
        }
        else if(procedureRepository.findById(proId).isEmpty()){
            throw new NoDependencyException(ExceptionConstants.PROCEDURE_DOES_NOT_EXIST);
        }
    }

}
