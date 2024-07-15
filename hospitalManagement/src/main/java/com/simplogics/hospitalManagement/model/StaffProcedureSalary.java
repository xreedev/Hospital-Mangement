package com.simplogics.hospitalManagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table
public class StaffProcedureSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer salaryPerHour;
    @ManyToOne
    @JoinColumn(name="staff_id",referencedColumnName = "staffId")
    private Staff staff;
    @ManyToOne
    @JoinColumn(name="procedure_id",referencedColumnName = "procedureId")
    private Procedure procedure;
}
