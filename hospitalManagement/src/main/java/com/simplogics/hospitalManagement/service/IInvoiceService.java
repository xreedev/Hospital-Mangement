package com.simplogics.hospitalManagement.service;

import com.simplogics.hospitalManagement.advice.HospitalException;

import java.io.IOException;

public interface IInvoiceService {
    void createInvoice(Integer patientId, String destination) throws Exception;

    void createExcel(String dest, String startDate, String endDate) throws IOException, HospitalException;
}
