package com.simplogics.hospitalManagement.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "procedure")
@RequiredArgsConstructor
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long procedureId;
    private String name;
    private Integer duration;
    @OneToMany
    @JoinColumn(name = "procedure_id")
    private List<PatientProcedure> patientProcedure;
    @OneToMany
    @JoinColumn(name = "procedure_id")
    private List<StaffProcedureSalary> staffProcedures;
    @OneToMany
    @JoinColumn(name = "procedure_id")
    private List<Quantity> quantities;

}
