package com.simplogics.hospitalManagement.util;
import com.simplogics.hospitalManagement.Invoice.Invoice;
import com.simplogics.hospitalManagement.Invoice.ProcedureInvoice;
import com.simplogics.hospitalManagement.constants.ExcelRows;
import com.simplogics.hospitalManagement.mapper.EquipDetails;
import com.simplogics.hospitalManagement.mapper.StaffDetails;
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
        Sheet sheet = workbook.createSheet(ExcelRows.WORKBOOK_TITLE);
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(ExcelRows.WORKBOOK_SUBTITLE);
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
            patientRow.createCell(0).setCellValue(ExcelRows.NAME_ROW + invoice.getName());
            patientRow.createCell(1).setCellValue(ExcelRows.DATE_ROW + invoice.getLocalDate());
            return rowNum;
        }

        private static int addProcedureDetails(Sheet sheet, int rowNum, ProcedureInvoice procedureInvoice) {
            Row procedureRow = sheet.createRow(rowNum++);
            procedureRow.createCell(0).setCellValue(ExcelRows.PROCEDURE_NAME_ROW + " " + procedureInvoice.getName());
            Integer procedureTotal = calculateProcedureTotal(procedureInvoice);
            Row procedureTotalRow = sheet.createRow(rowNum++);
            procedureTotalRow.createCell(0).setCellValue(ExcelRows.PROCEDURE_TOTAL + " " + procedureTotal);
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
            netTotalRow.createCell(0).setCellValue(ExcelRows.NET_TOTAL + " " + netTotal);
        }

        private static void saveWorkbook(Workbook workbook, String dest) throws IOException {
            try (FileOutputStream fileOut = new FileOutputStream(dest)) {
                workbook.write(fileOut);
            }




































            
        }
    }