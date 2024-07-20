package com.simplogics.hospitalManagement.service;

import com.simplogics.hospitalManagement.advice.FieldRequiredException;
import com.simplogics.hospitalManagement.advice.InvalidDataFormatException;
import com.simplogics.hospitalManagement.advice.NoDependencyException;
import com.simplogics.hospitalManagement.advice.NullRequestException;
import com.simplogics.hospitalManagement.constants.ExceptionConstants;
import com.simplogics.hospitalManagement.dto.PatientDTO;
import com.simplogics.hospitalManagement.dto.PatientProcedureDto;
import com.simplogics.hospitalManagement.dto.ResponseDTO;
import com.simplogics.hospitalManagement.model.Patient;
import com.simplogics.hospitalManagement.repository.IPatientProcedureRepository;
import com.simplogics.hospitalManagement.repository.IPatientRepository;
import com.simplogics.hospitalManagement.repository.IProcedureRepository;
import com.simplogics.hospitalManagement.util.Parser;
import com.simplogics.hospitalManagement.util.PatientUtil;
import com.simplogics.hospitalManagement.util.ToDtoGlobal;
import com.simplogics.hospitalManagement.validators.PatientValidators;

import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class PatientService {
    private final IPatientRepository patientRepository;
    private final IProcedureRepository procedureRepository;
    private final IPatientProcedureRepository patientProcedureRepository;

    public ResponseDTO createPatient(PatientDTO patientDto) throws FieldRequiredException {
        PatientValidators.patientDtoCheck(patientDto);
        List<Patient> patients = PatientUtil.createPatient(patientDto);
        patients.forEach(patientRepository::save);
        return ToDtoGlobal.listToPatientDTO(patients);
    }

    public ResponseDTO setPatPro(PatientProcedureDto procedureDto) throws FieldRequiredException, NoDependencyException, InvalidDataFormatException, InvalidPropertiesFormatException {
        PatientValidators.patientProcedureDtoCheck(procedureDto, patientRepository, procedureRepository);
        Date date = Parser.dateParser(procedureDto.getDate());
        Integer patId = Parser.intParser(procedureDto.getPatientId());
        Integer proId = Parser.intParser(procedureDto.getProcedureId());
        System.out.println(patientProcedureRepository.updateProcedure(proId, patId, date));
        return ToDtoGlobal.toPatientProcedureDto(procedureDto);
    }

    public ResponseDTO updatePatient(Integer pid, String Name) {
        Patient P = new Patient();
        P.setPatientId(pid);
        P.setName(Name);
        Patient saved = patientRepository.save(P);
        return ToDtoGlobal.toPatientDto(saved);
    }

    public ResponseDTO delPatient(Integer pId) throws NullRequestException {
        patientRepository.delete(patientRepository.findById(pId.longValue()).orElseThrow(() -> new NullRequestException(ExceptionConstants.PATIENT_DOES_NOT_EXIST)));
        ResponseDTO responseDTO = new ResponseDTO();
        //responseDTO.setMessage(GlobalConstants.DELETED);
        return responseDTO;
    }

    public ResponseDTO getPatients() {
        List<Patient> patients = patientRepository.findAll();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(PatientUtil.createPatientDto(patients));
        return responseDTO;
    }
}
