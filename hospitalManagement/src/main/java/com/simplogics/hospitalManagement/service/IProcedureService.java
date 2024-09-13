package com.simplogics.hospitalManagement.service;

import com.simplogics.hospitalManagement.dto.ResponseDTO;

public interface IProcedureService {

    ResponseDTO getDetails(Integer patientId);

    ResponseDTO getStaffDetails(Integer patientId, Integer procedureId);

    ResponseDTO getEquipDetails(Integer patientId, Integer procedureId);
}
