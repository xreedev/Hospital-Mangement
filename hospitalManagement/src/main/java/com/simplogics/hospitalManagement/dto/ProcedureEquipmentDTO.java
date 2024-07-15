package com.simplogics.hospitalManagement.dto;

public class ProcedureEquipmentDTO {
    private String procedureName;
    private String equipmentName;
    private int numberOfEquipment;
    private double costPerUse;
    private double total;

    // Constructor
    public ProcedureEquipmentDTO(String procedureName, String equipmentName, int numberOfEquipment, double costPerUse, double total) {
        this.procedureName = procedureName;
        this.equipmentName = equipmentName;
        this.numberOfEquipment = numberOfEquipment;
        this.costPerUse = costPerUse;
        this.total = total;
    }

    // Getters and setters
    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getNumberOfEquipment() {
        return numberOfEquipment;
    }

    public void setNumberOfEquipment(int numberOfEquipment) {
        this.numberOfEquipment = numberOfEquipment;
    }

    public double getCostPerUse() {
        return costPerUse;
    }

    public void setCostPerUse(double costPerUse) {
        this.costPerUse = costPerUse;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
