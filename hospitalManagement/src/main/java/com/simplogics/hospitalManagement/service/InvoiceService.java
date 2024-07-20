package com.simplogics.hospitalManagement.service;

import com.simplogics.hospitalManagement.advice.InvalidDataFormatException;
import com.simplogics.hospitalManagement.advice.NoDependencyException;
import com.simplogics.hospitalManagement.advice.NullRequestException;

import java.io.IOException;

public interface InvoiceService {
    void createInvoice(Integer patientId, String destination) throws Exception;

    void createExcel(String dest, String startDate, String endDate) throws IOException, NullRequestException, InvalidDataFormatException, NoDependencyException;
}
