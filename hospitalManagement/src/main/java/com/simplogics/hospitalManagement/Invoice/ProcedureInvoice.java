package com.simplogics.hospitalManagement.Invoice;

import com.simplogics.hospitalManagement.mappingObjects.EquipDetails;
import com.simplogics.hospitalManagement.mappingObjects.StaffDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureInvoice {
    private Integer duration;
    private String name;
    private List<StaffDetails> staffDetails;
    private List<EquipDetails> equipDetails;
}
