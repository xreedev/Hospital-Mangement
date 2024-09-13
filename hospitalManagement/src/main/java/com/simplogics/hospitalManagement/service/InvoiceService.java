package com.simplogics.hospitalManagement.service;
import com.simplogics.hospitalManagement.Invoice.Invoice;
import com.simplogics.hospitalManagement.advice.HospitalException;
import com.simplogics.hospitalManagement.constants.ExceptionConstants;
import com.simplogics.hospitalManagement.constants.GlobalConstants;
import com.simplogics.hospitalManagement.model.Patient;
import com.simplogics.hospitalManagement.repository.IPatientProcedureRepository;
import com.simplogics.hospitalManagement.repository.IPatientRepository;
import com.simplogics.hospitalManagement.util.ExcelUtil;
import com.simplogics.hospitalManagement.util.InvoiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService implements IInvoiceService {
    private final IPatientRepository patientRepository;
    private final IPatientProcedureRepository patientProcedureRepository;
    @Override
    public void createInvoice(Integer patientId, String destination) throws Exception {
        Invoice invoice=InvoiceUtil.setInvoice(patientId,patientProcedureRepository);
         InvoiceUtil.generateInvoice(invoice,destination);
    }
    @Override
    public void createExcel(String dest, String startDate, String endDate) throws IOException, HospitalException{
        List<Invoice> invoices=new ArrayList<>();
        List<Patient> patientIds=patientRepository.findAll();
        for(Patient patient:patientIds) {
            System.out.println(patient.getPatientId());
            Invoice invoice=InvoiceUtil.setInvoice(patient.getPatientId(),patientProcedureRepository);
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(GlobalConstants.DATE_FORMAT);
                Date start = simpleDateFormat.parse(startDate);
                Date end = simpleDateFormat.parse(endDate);
                if(invoice.getStartDate().before(end) && invoice.getStartDate().after(start)){
                    invoices.add(invoice);}
            }
            catch (ParseException p){
                throw new HospitalException(ExceptionConstants.DATE_FORMAT_NOT_CORRECT);
            }
        }
        ExcelUtil.generateInvoices(invoices,dest);
    }

}
