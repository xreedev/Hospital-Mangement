package com.simplogics.hospitalManagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
@Table
public class PatientProcedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "procedure_id", referencedColumnName = "procedureId")
    private Procedure procedure;
}
