package com.simplogics.hospitalManagement.util;
import com.simplogics.hospitalManagement.Invoice.Invoice;
import com.simplogics.hospitalManagement.Invoice.ProcedureInvoice;
import com.simplogics.hospitalManagement.constants.GlobalConstants;
import com.simplogics.hospitalManagement.mappingObjects.EquipDetails;
import com.simplogics.hospitalManagement.mappingObjects.StaffDetails;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
public class ExcelUtil {

    public static void generateInvoices(List<Invoice> invoices, String dest) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(GlobalConstants.WORKBOOK_TITLE);
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(GlobalConstants.WORKBOOK_SUBTITLE);
        int rowNum = 0;
        for (Invoice invoice : invoices) {
            rowNum = addInvoice(sheet, rowNum, invoice);
            rowNum++;
        }

        saveWorkbook(workbook, dest);
    }

    private static int addInvoice(Sheet sheet, int rowNum, Invoice invoice) {
        rowNum = addTitle(sheet, rowNum);
        rowNum = addPatientInfo(sheet, rowNum, invoice);

        Integer netTotal = 0;
        for (ProcedureInvoice procedureInvoice : invoice.getProcedureInvoices()) {
            rowNum = addProcedureDetails(sheet, rowNum, procedureInvoice);
            netTotal += calculateProcedureTotal(procedureInvoice);
        }

        addNetTotal(sheet, rowNum, netTotal);
        return rowNum;
    }

        private static int addTitle(Sheet sheet, int rowNum) {

            CellStyle style = sheet.getWorkbook().createCellStyle();
            Font font = sheet.getWorkbook().createFont();
            font.setBold(true);
            font.setFontHeightInPoints((short) 14);
            font.setColor(IndexedColors.RED.getIndex());
            style.setFont(font);
            sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 3));
            return rowNum;
        }

        private static int addPatientInfo(Sheet sheet, int rowNum, Invoice invoice) {
            Row patientRow = sheet.createRow(rowNum++);
            patientRow.createCell(0).setCellValue(GlobalConstants.PATIENT_NAME_ROW + invoice.getName());
            patientRow.createCell(1).setCellValue(GlobalConstants.DATE_ROW + invoice.getLocalDate());
            return rowNum;
        }

        private static int addProcedureDetails(Sheet sheet, int rowNum, ProcedureInvoice procedureInvoice) {
            Row procedureRow = sheet.createRow(rowNum++);
            procedureRow.createCell(0).setCellValue("Procedure Name: " + procedureInvoice.getName());

            rowNum = addStaffDetails(sheet, rowNum, procedureInvoice.getStaffDetails());
            rowNum = addEquipDetails(sheet, rowNum, procedureInvoice.getEquipDetails());

            Integer procedureTotal = calculateProcedureTotal(procedureInvoice);
            Row procedureTotalRow = sheet.createRow(rowNum++);
            procedureTotalRow.createCell(0).setCellValue("Procedure Total: " + procedureTotal);

            return rowNum;
        }

        private static int addStaffDetails(Sheet sheet, int rowNum, List<StaffDetails> staffDetails) {
            Row staffTitleRow = sheet.createRow(rowNum++);
            staffTitleRow.createCell(0).setCellValue("Staff:");

            Row headerRow = sheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Name");
            headerRow.createCell(1).setCellValue("Duration");
            headerRow.createCell(2).setCellValue("Salary per Hour");
            headerRow.createCell(3).setCellValue("Total");

            for (StaffDetails staff : staffDetails) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(staff.getStaffName());
                row.createCell(1).setCellValue(staff.getDuration());
                row.createCell(2).setCellValue(staff.getSalaryPerHour().toString());
                row.createCell(3).setCellValue(staff.getTotal().toString());
            }

            Integer staffTotal = calculateStaffTotal(staffDetails);
            Row staffTotalRow = sheet.createRow(rowNum++);
            staffTotalRow.createCell(0).setCellValue("Staff Total: " + staffTotal);

            return rowNum;
        }

        private static int addEquipDetails(Sheet sheet, int rowNum, List<EquipDetails> equipDetails) {
            Row equipTitleRow = sheet.createRow(rowNum++);
            equipTitleRow.createCell(0).setCellValue("Equipment:");

            Row headerRow = sheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Equipment Name");
            headerRow.createCell(1).setCellValue("Number of Equipment");
            headerRow.createCell(2).setCellValue("Cost per Use");
            headerRow.createCell(3).setCellValue("Total");

            for (EquipDetails equip : equipDetails) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(equip.getEquipmentName());
                row.createCell(1).setCellValue(equip.getNumberOfEquipment());
                row.createCell(2).setCellValue(equip.getCostPerUse().toString());
                row.createCell(3).setCellValue(equip.getTotal().toString());
            }

            BigDecimal equipTotal = calculateEquipTotal(equipDetails);
            Row equipTotalRow = sheet.createRow(rowNum++);
            equipTotalRow.createCell(0).setCellValue("Equipment Total: " + equipTotal);

            return rowNum;
        }

        private static Integer calculateProcedureTotal(ProcedureInvoice procedureInvoice) {
            return calculateStaffTotal(procedureInvoice.getStaffDetails()) +
                    calculateEquipTotal(procedureInvoice.getEquipDetails()).intValue();
        }

        private static Integer calculateStaffTotal(List<StaffDetails> staffDetails) {
            return staffDetails.stream().mapToInt(staff -> staff.getTotal().intValue()).sum();
        }

        private static BigDecimal calculateEquipTotal(List<EquipDetails> equipDetails) {
            return equipDetails.stream()
                    .map(equip -> new BigDecimal(equip.getTotal().toString()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        private static void addNetTotal(Sheet sheet, int rowNum, Integer netTotal) {
            Row netTotalRow = sheet.createRow(rowNum++);
            netTotalRow.createCell(0).setCellValue("Net Total: " + netTotal);
        }

        private static void saveWorkbook(Workbook workbook, String dest) throws IOException {
            try (FileOutputStream fileOut = new FileOutputStream(dest)) {
                workbook.write(fileOut);
            }
        }
    }