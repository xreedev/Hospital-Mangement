package com.simplogics.hospitalManagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;
    private String staffName;
    @OneToMany
    @JoinColumn(name="staff_id")
    private List<StaffProcedureSalary> staffProcedures;
}
