package com.simplogics.hospitalManagement.mapper;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class StaffDetails {
    @Id
    private String staffName;
    private Integer duration;
    private BigDecimal salaryPerHour;
    private BigDecimal total;
}
