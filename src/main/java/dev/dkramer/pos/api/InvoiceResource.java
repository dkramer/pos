package dev.dkramer.pos.api;

import dev.dkramer.pos.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/pos/invoice")
public class InvoiceResource {

    @Autowired
    InvoiceService invoiceService;

    //TODO could be part of path or could be a query param
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    //TODO: would make this Map<long, TenderType> as an enum but instructions say to use String
    public Map<Long, String> getInvoicesAndTenderTypesForCustomer(@RequestParam(required = true) long customerId){

        //TODO: pagination
        //TODO: validate not null/required
        //TODO: allow not null and return everything?
        //TODO: would have this return an object so if we need to return more info in the future it may avoid an API breaking change

        return invoiceService.getInvoicesAndTenderTypesForCustomer(customerId);
    }

}
