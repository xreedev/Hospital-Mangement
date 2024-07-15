package com.simplogics.hospitalManagement.mappingObjects;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class EquipDetails {
     String  equipmentName;
     Integer numberOfEquipment;
     Integer costPerUse;
     Integer total;
}
