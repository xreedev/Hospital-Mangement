package com.simplogics.hospitalManagement.service;

import com.simplogics.hospitalManagement.mappingObjects.EquipDetails;
import com.simplogics.hospitalManagement.dto.ResponseDTO;
import com.simplogics.hospitalManagement.mappingObjects.StaffDetails;
import com.simplogics.hospitalManagement.mappingObjects.TotalProcedureDetails;
import com.simplogics.hospitalManagement.repository.IPatientProcedureRepository;
import com.simplogics.hospitalManagement.repository.IProcedureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcedureService {
    private final IProcedureRepository procedureRepository;
    private final IPatientProcedureRepository patientProcedureRepository;
    public ResponseDTO getProcedure(){
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setData(procedureRepository.findAll());
        return responseDTO;
    }
    public ResponseDTO getDetails(Integer patientId){
        ResponseDTO responseDTO=new ResponseDTO();
        List<Object[]> p=patientProcedureRepository.fetchPatientProcedureDetails(patientId);
        List<TotalProcedureDetails> procedureDetails=p.stream().map(res->
                new TotalProcedureDetails( res[0],res[1], res[2], res[3],
                        (BigDecimal) res[4],res[5], res[6])).toList();
        responseDTO.setData(procedureDetails);
        return responseDTO;
    }
    public ResponseDTO getStaffDetails(Integer patientId,Integer procedureId){
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setData(patientProcedureRepository.fetchStaffTotalDetails(patientId,procedureId));
        List<Object[]> p=patientProcedureRepository.fetchStaffTotalDetails(patientId,procedureId);
        List<StaffDetails> staffDetails=p.stream().map(result->new StaffDetails(
                (String) result[1],(Integer) result[2],(BigDecimal)result[3],(BigDecimal) result[4])).collect(Collectors.toList());
        responseDTO.setData(staffDetails);
        return responseDTO;
    }
    public ResponseDTO getEquipDetails(Integer patientId,Integer procedureId){
        ResponseDTO responseDTO=new ResponseDTO();
        List<Object[]> p=patientProcedureRepository.fetchEquipmentTotalDetails(patientId,procedureId);
        List<EquipDetails> equipDetails=p.stream().map(result->new EquipDetails((String) result[1], (Integer) result[2],(Integer)result[3],(Integer) result[4])).collect(Collectors.toList());
        responseDTO.setData(equipDetails);
        return responseDTO;
    }
}
