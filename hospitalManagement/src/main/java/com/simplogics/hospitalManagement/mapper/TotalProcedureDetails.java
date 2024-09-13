package com.simplogics.hospitalManagement.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TotalProcedureDetails {
    private Object pid;
    private Object patientName;
    private Object procedureId;
    private Object procedureName;
    private BigDecimal totalCost;
    private Object totalSalary;
    private Object totalEquip;
}
