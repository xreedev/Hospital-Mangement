package com.simplogics.hospitalManagement.dto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@Setter
public class PatientDTO {
    private List<String> patientName;
}
