package com.simplogics.hospitalManagement.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    private String name;
    LocalDate localDate=LocalDate.now();
    private Integer total;
    private List<Integer> pId;
    private List<ProcedureInvoice> procedureInvoices;
    private Date startDate;
}
