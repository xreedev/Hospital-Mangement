package com.simplogics.hospitalManagement.controller;
import com.simplogics.hospitalManagement.constants.ApiRoutes;
import com.simplogics.hospitalManagement.dto.ResponseDTO;
import com.simplogics.hospitalManagement.service.IProcedureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRoutes.PROCEDURE_API)
public class ProcedureController {
    private final IProcedureService IProcedureService;
@GetMapping(ApiRoutes.FETCH_TOTAL)
public ResponseDTO fetch(@RequestParam Integer patientId){return IProcedureService.getDetails(patientId);}
    @GetMapping(ApiRoutes.FETCH_STAFF)
    public ResponseDTO fetchStaff(@RequestParam Integer patientId,@RequestParam Integer procedureId)
    {return IProcedureService.getStaffDetails(patientId,procedureId);}
    @GetMapping(ApiRoutes.FETCH_EQUIP)
    public ResponseDTO fetchEquip(@RequestParam Integer patientId,@RequestParam Integer procedureId)
    {return IProcedureService.getEquipDetails(patientId,procedureId);}
}
