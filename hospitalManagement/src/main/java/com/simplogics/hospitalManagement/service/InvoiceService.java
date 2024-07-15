package com.simplogics.hospitalManagement.service;

import com.simplogics.hospitalManagement.Invoice.Invoice;
import com.simplogics.hospitalManagement.mappingObjects.EquipDetails;
import com.simplogics.hospitalManagement.mappingObjects.StaffDetails;
import com.simplogics.hospitalManagement.model.Patient;
import com.simplogics.hospitalManagement.repository.IPatientProcedureRepository;
import com.simplogics.hospitalManagement.repository.IPatientRepository;
import com.simplogics.hospitalManagement.repository.IProcedureRepository;
import com.simplogics.hospitalManagement.util.ExcelUtil;
import com.simplogics.hospitalManagement.util.InvoiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final IPatientRepository patientRepository;
    private final IPatientProcedureRepository patientProcedureRepository;
    public void createInvoice(Integer patientId) throws Exception {
        Invoice invoice=InvoiceUtil.setInvoice(patientId,patientProcedureRepository);
         InvoiceUtil.generateInvoice(invoice,"test.pdf");
         createExcel();
    }
    public void createExcel() throws IOException {
        List<Invoice> invoices=new ArrayList<>();
        List<Patient> patientIds=patientRepository.findAll();
        for(Patient patient:patientIds) {
            System.out.println(patient.getPatientId());
            Invoice invoice=InvoiceUtil.setInvoice(patient.getPatientId(),patientProcedureRepository);
            invoices.add(invoice);
        }
        ExcelUtil.generateInvoices(invoices,"test.xlsx");
    }

}
