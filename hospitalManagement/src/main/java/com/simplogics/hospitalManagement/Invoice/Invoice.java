package com.simplogics.hospitalManagement.Invoice;

import com.simplogics.hospitalManagement.mappingObjects.EquipDetails;
import com.simplogics.hospitalManagement.mappingObjects.StaffDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    private String name;
    LocalDate localDate=LocalDate.now();
    private Integer total;
    private List<Integer> pId;
    private List<ProcedureInvoice> procedureInvoices;
}
