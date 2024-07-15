package com.simplogics.hospitalManagement.controller;
import com.simplogics.hospitalManagement.constants.ApiRoutes;
import com.simplogics.hospitalManagement.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRoutes.INVOICE_API)
public class InvoiceController {
    private final InvoiceService invoiceService;
    @GetMapping(ApiRoutes.INVOICE_PDF)
    public void getPdf(@RequestParam(required = false) Integer patientId) throws Exception {
        invoiceService.createInvoice(patientId);
    }
    @GetMapping(ApiRoutes.INVOICE_EXCEL)
    public void getPdf() throws Exception {
        invoiceService.createExcel();
    }
}
