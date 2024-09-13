package com.simplogics.hospitalManagement.controller;
import com.simplogics.hospitalManagement.constants.ApiRoutes;
import com.simplogics.hospitalManagement.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRoutes.INVOICE_API)
public class InvoiceController {
    private final InvoiceService InvoiceService;
    @GetMapping(ApiRoutes.INVOICE_PDF)
    public void getPdf(@RequestParam(required = false) Integer patientId,@RequestParam(required = false) String  dest) throws Exception {
        InvoiceService.createInvoice(patientId,dest);
    }
    @GetMapping(ApiRoutes.INVOICE_EXCEL)
    public void getExcel(@RequestParam String  dest,@RequestParam String  start,@RequestParam String  end) throws Exception {
        InvoiceService.createExcel(dest,start,end);
    }
}
