package com.simplogics.hospitalManagement.service;

import com.simplogics.hospitalManagement.Invoice.Invoice;
import com.simplogics.hospitalManagement.constants.ExcelRows;
import com.simplogics.hospitalManagement.model.Patient;
import com.simplogics.hospitalManagement.repository.IPatientProcedureRepository;
import com.simplogics.hospitalManagement.repository.IPatientRepository;
import com.simplogics.hospitalManagement.util.ExcelUtil;
import com.simplogics.hospitalManagement.util.InvoiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final IPatientRepository patientRepository;
    private final IPatientProcedureRepository patientProcedureRepository;
    public void createInvoice(Integer patientId,String dest) throws Exception {
        Invoice invoice=InvoiceUtil.setInvoice(patientId,patientProcedureRepository);
         InvoiceUtil.generateInvoice(invoice,dest);
    }
    public void createExcel(String dest) throws IOException {
        List<Invoice> invoices=new ArrayList<>();
        List<Patient> patientIds=patientRepository.findAll();
        for(Patient patient:patientIds) {
            System.out.println(patient.getPatientId());
            Invoice invoice=InvoiceUtil.setInvoice(patient.getPatientId(),patientProcedureRepository);
            invoices.add(invoice);
        }
        ExcelUtil.generateInvoices(invoices,dest);
    }

}
