package com.simplogics.hospitalManagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@Table
@RequiredArgsConstructor
public class Quantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quanity;
    @ManyToOne
    @JoinColumn(name = "equipment_id", referencedColumnName = "equipId")
    private Equipments equipments;
}
