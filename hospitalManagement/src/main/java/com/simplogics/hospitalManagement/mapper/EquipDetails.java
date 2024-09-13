package com.simplogics.hospitalManagement.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EquipDetails {
     String  equipmentName;
     Integer numberOfEquipment;
     Integer costPerUse;
     Integer total;
}
