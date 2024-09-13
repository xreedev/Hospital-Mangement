package com.simplogics.hospitalManagement.service;

import com.simplogics.hospitalManagement.advice.HospitalException;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PatientService implements IPatientService {
    private final IPatientRepository patientRepository;
    private final IProcedureRepository procedureRepository;
    private final IPatientProcedureRepository patientProcedureRepository;

    @Override
    public ResponseDTO createPatient(PatientDTO patientDto) throws HospitalException {
        PatientValidators.patientDtoCheck(patientDto);
        List<Patient> patients = PatientUtil.createPatient(patientDto);
        patientRepository.saveAll(patients);
        return ToDtoGlobal.listToPatientDTO(patients);
    }

    @Override
    public ResponseDTO setPatPro(PatientProcedureDto procedureDto) throws HospitalException {
        PatientValidators.patientProcedureDtoCheck(procedureDto, patientRepository, procedureRepository);
        Date date = Parser.dateParser(procedureDto.getDate());
        Integer patId = Parser.intParser(procedureDto.getPatientId());
        Integer proId = Parser.intParser(procedureDto.getProcedureId());
        System.out.println(patientProcedureRepository.updateProcedure(proId, patId, date));
        return ToDtoGlobal.toPatientProcedureDto(procedureDto);
    }

    @Override
    public ResponseDTO updatePatient(Integer pid, String Name) {
        Patient P = new Patient();
        P.setPatientId(pid);
        P.setName(Name);
        Patient saved = patientRepository.save(P);
        return ToDtoGlobal.toPatientDto(saved);
    }

    @Override
    public ResponseDTO delPatient(Integer pId) throws HospitalException {
        patientRepository.delete(patientRepository.findById(pId.longValue()).orElseThrow(() -> new HospitalException(ExceptionConstants.PATIENT_DOES_NOT_EXIST)));
        return new ResponseDTO();
    }

    @Override
    public ResponseDTO getPatients() {
        List<Patient> patients = patientRepository.findAll();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(PatientUtil.createPatientDto(patients));
        return responseDTO;
    }
}
