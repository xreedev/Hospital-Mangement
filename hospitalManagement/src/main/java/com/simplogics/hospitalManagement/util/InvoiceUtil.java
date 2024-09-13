package com.simplogics.hospitalManagement.util;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.simplogics.hospitalManagement.Invoice.Invoice;
import com.simplogics.hospitalManagement.Invoice.ProcedureInvoice;
import com.simplogics.hospitalManagement.constants.ExcelRows;
import com.simplogics.hospitalManagement.constants.ExceptionConstants;
import com.simplogics.hospitalManagement.mapper.EquipDetails;
import com.simplogics.hospitalManagement.mapper.StaffDetails;
import com.simplogics.hospitalManagement.repository.IPatientProcedureRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceUtil {

    public static Invoice setInvoice(Integer patientId, IPatientProcedureRepository patientProcedureRepository) throws NullRequestException {
        Invoice invoice = new Invoice();
        List<Object[]> patientProcedureDetails = patientProcedureRepository.fetchPatientProcedureDetails(patientId);
        if(patientProcedureDetails.isEmpty()){
            throw new NullRequestException(ExceptionConstants.NO_RECORDS_FOUNDS_OF_THIS_PATIENT);
        }
        Integer totalCost = 0;
        List<Integer> procedures = new ArrayList<>();
        for (Object[] patient : patientProcedureDetails) {
            Integer costInt = ((BigDecimal) patient[4]).intValue();
            totalCost += costInt;
            procedures.add((Integer) patient[2]);
        }
        invoice.setTotal(totalCost);
        invoice.setPId(procedures);
        invoice.setName((String) patientProcedureDetails.getFirst()[1]);
        invoice.setStartDate((Date) patientProcedureDetails.getFirst()[7]);
        List<ProcedureInvoice> procedureInvoices = InvoiceUtil.getProcedureInvoices(procedures, patientProcedureRepository, patientId);
        invoice.setProcedureInvoices(procedureInvoices);
        return invoice;
    }

    public static List<ProcedureInvoice> getProcedureInvoices(List<Integer> procedures, IPatientProcedureRepository patientProcedureRepository, Integer patientId) throws NullRequestException {
        List<ProcedureInvoice> procedureInvoices = new ArrayList<>();
        for (Integer pId : procedures) {
            List<Object[]> patientProcedure = patientProcedureRepository.fetchStaffTotalDetails(patientId, pId);
            if(patientProcedure.isEmpty()){
                throw new NullRequestException(ExceptionConstants.NO_RECORDS_FOUNDS_OF_THIS_PATIENT);
            }
            List<StaffDetails> staffDetails = patientProcedure.stream().map(result ->
                    new StaffDetails((String) result[1], (Integer) result[2],
                            (BigDecimal) result[3], (BigDecimal) result[4])).toList();
            List<Object[]> p1 = patientProcedureRepository.fetchEquipmentTotalDetails(patientId, pId);
            List<EquipDetails> equipDetails = p1.stream().map(result -> new EquipDetails((String) result[1], (Integer) result[2],
                    (Integer) result[3], (Integer) result[4])).toList();

            ProcedureInvoice procedureInvoice = new ProcedureInvoice();
            procedureInvoice.setDuration((Integer) patientProcedure.getFirst()[2]);
            procedureInvoice.setName((String) patientProcedure.getFirst()[0]);
            procedureInvoice.setStaffDetails(staffDetails);
            procedureInvoice.setEquipDetails(equipDetails);
            procedureInvoices.add(procedureInvoice);
        }
        return procedureInvoices;
    }

    public static void generateInvoice(Invoice invoice, String dest) throws Exception {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        addTitle(document);
        addPatientInfo(document, invoice);

        Integer netTotal = 0;
        for (ProcedureInvoice procedureInvoice : invoice.getProcedureInvoices()) {
            Integer procedureTotal = addProcedureDetails(document, procedureInvoice);
            netTotal += procedureTotal;
        }

        addNetTotal(document, netTotal);
        document.close();
    }

    private static void addTitle(Document document) throws Exception {
        Paragraph title = new Paragraph("***************************\n" +
                "        Hospital Management      \n" +
                "     INVOICE      \n" +
                "***************************\n\n")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                .setFontSize(14)
                .setFontColor(ColorConstants.RED)
                .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER);
        document.add(title);
    }

    private static void addPatientInfo(Document document, Invoice invoice) {
        Paragraph patientInfo = new Paragraph(ExcelRows.NAME + invoice.getName() + "\n" +
                ExcelRows.DATE_ROW + invoice.getLocalDate() + "\n\n")
                .setFontSize(12);
        document.add(patientInfo);
    }

    private static Integer addProcedureDetails(Document document, ProcedureInvoice procedureInvoice) {
        Paragraph procedureTitle = new Paragraph(ExcelRows.PROCEDURE_NAME_ROW + procedureInvoice.getName() + "\n")
                .setFontSize(8)
                .setFontColor(ColorConstants.BLACK);
        document.add(procedureTitle);

        Integer staffTotal = addStaffDetails(document, procedureInvoice.getStaffDetails());
        Integer equipTotal = addEquipDetails(document, procedureInvoice.getEquipDetails());

        Integer procedureTotal = staffTotal + equipTotal;
        Paragraph procedureTotalPara = new Paragraph(ExcelRows.PROCEDURE_TOTAL + procedureTotal + "\n\n")
                .setFontSize(8)
                .setFontColor(ColorConstants.BLACK);
        document.add(procedureTotalPara);

        return procedureTotal;
    }

    private static Integer addStaffDetails(Document document, List<StaffDetails> staffDetails) {
        Paragraph staffTitle = new Paragraph(ExcelRows.STAFF + "\n")
                .setFontSize(8)
                .setFontColor(ColorConstants.BLACK);
        document.add(staffTitle);

        Table staffTable = new Table(new float[]{5, 5, 5, 5});
        staffTable.addCell(ExcelRows.NAME_ROW);
        staffTable.addCell(ExcelRows.DURATION);
        staffTable.addCell(ExcelRows.SALARY_PER_HOUR);
        staffTable.addCell(ExcelRows.TOTAL);

        Integer staffTotal = 0;
        for (StaffDetails staff : staffDetails) {
            staffTable.addCell(staff.getStaffName()).setFontSize(8);
            staffTable.addCell(staff.getDuration().toString()).setFontSize(8);
            staffTable.addCell(staff.getSalaryPerHour().toString()).setFontSize(8);
            staffTable.addCell(staff.getTotal().toString()).setFontSize(8);
            staffTotal += staff.getTotal().intValue();
        }
        document.add(staffTable);

        Paragraph staffTotalPara = new Paragraph(ExcelRows.STAFF_TOTAL + staffTotal + "\n\n")
                .setFontSize(8)
                .setFontColor(ColorConstants.BLACK);
        document.add(staffTotalPara);

        return staffTotal;
    }

    private static Integer addEquipDetails(Document document, List<EquipDetails> equipDetails) {
        Paragraph equipTitle = new Paragraph(ExcelRows.EQUIPMENT + "\n")
                .setFontSize(8)
                .setFontColor(ColorConstants.BLACK);
        document.add(equipTitle);

        Table equipTable = new Table(new float[]{5, 5, 5, 5});
        equipTable.addCell(ExcelRows.EQUIPMENT_NAME);
        equipTable.addCell(ExcelRows.NUMBER_OF_EQUIPMENT);
        equipTable.addCell(ExcelRows.COST_PER_USE);
        equipTable.addCell(ExcelRows.TOTAL);

        BigDecimal equipTotal = BigDecimal.ZERO;
        for (EquipDetails equip : equipDetails) {
            equipTable.addCell(equip.getEquipmentName()).setFontSize(8);
            equipTable.addCell(equip.getNumberOfEquipment().toString()).setFontSize(8);
            equipTable.addCell(equip.getCostPerUse().toString()).setFontSize(8);
            equipTable.addCell(equip.getTotal().toString()).setFontSize(8);
            equipTotal = equipTotal.add(new BigDecimal(equip.getTotal().toString()));
        }
        document.add(equipTable);

        Paragraph equipTotalPara = new Paragraph(ExcelRows.EQUIPMENT_TOTAL + equipTotal + "\n\n")
                .setFontSize(8)
                .setFontColor(ColorConstants.BLACK);
        document.add(equipTotalPara);

        return equipTotal.intValue();
    }

    private static void addNetTotal(Document document, Integer netTotal) {
        Paragraph netTotalPara = new Paragraph(ExcelRows.NET_TOTAL + netTotal + "\n\n")
                .setFontSize(8)
                .setFontColor(ColorConstants.BLACK);
        document.add(netTotalPara);
    }
}
