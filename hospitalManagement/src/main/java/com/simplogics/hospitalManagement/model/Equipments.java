package com.simplogics.hospitalManagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@Table
@RequiredArgsConstructor
public class Equipments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipId;
    private String equipName;
    private Integer costPerUse;
    @OneToMany
    @JoinColumn(name = "equip_id")
    private List<Quantity> quantity;
}
