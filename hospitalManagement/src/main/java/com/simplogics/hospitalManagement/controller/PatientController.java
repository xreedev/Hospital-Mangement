package com.simplogics.hospitalManagement.controller;
import com.simplogics.hospitalManagement.advice.FieldRequiredException;
import com.simplogics.hospitalManagement.advice.InvalidDataFormatException;
import com.simplogics.hospitalManagement.advice.NoDependencyException;
import com.simplogics.hospitalManagement.advice.NullRequestException;
import com.simplogics.hospitalManagement.constants.ApiRoutes;
import com.simplogics.hospitalManagement.dto.PatientDTO;
import com.simplogics.hospitalManagement.dto.PatientProcedureDto;
import com.simplogics.hospitalManagement.dto.ResponseDTO;
import com.simplogics.hospitalManagement.service.InvoiceService;
import com.simplogics.hospitalManagement.service.PatientService;
import com.simplogics.hospitalManagement.service.ProcedureService;
import com.simplogics.hospitalManagement.validators.RequestValidators;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.InvalidPropertiesFormatException;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRoutes.PATIENT_API)
public class PatientController {
    private final PatientService patientService;
    @PostMapping(ApiRoutes.CREATE_PATIENT)
    public ResponseDTO createPatient(@RequestBody(required = false) PatientDTO patientDTO) throws NullRequestException, FieldRequiredException {
        RequestValidators.check(patientDTO);
        return patientService.createPatient(patientDTO);
    }
    @PostMapping(ApiRoutes.CREATE_PROCEDURE)
    public ResponseDTO setPatientProcedure(@RequestBody(required = false) PatientProcedureDto procedureDto) throws NullRequestException, FieldRequiredException, NoDependencyException, InvalidDataFormatException, InvalidPropertiesFormatException {
        RequestValidators.check(procedureDto);
        return patientService.setPatPro(procedureDto);
    }
    @PutMapping(ApiRoutes.UPDATE_PATIENT)
    public ResponseDTO updatePatient(@RequestParam(required = false) Integer pid,@RequestParam(required = false) String name) throws NullRequestException {
        RequestValidators.checkParams(pid,name);
        return patientService.updatePatient(pid,name);
    }
    @DeleteMapping(ApiRoutes.DEL_PATIENT)
    public ResponseDTO deletePatient(@RequestParam(required = false) Integer pid) throws NullRequestException {
        RequestValidators.check(pid);
        return patientService.delPatient(pid);
    }
    @GetMapping(ApiRoutes.GET_PATIENTS)
    public ResponseDTO getPatients(){
        return patientService.getPatients();
    }
}