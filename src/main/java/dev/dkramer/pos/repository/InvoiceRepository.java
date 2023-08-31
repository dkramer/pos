package dev.dkramer.pos.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.dkramer.pos.repository.models.Invoice;
import dev.dkramer.pos.repository.models.InvoiceData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InvoiceRepository {

    Logger log = LoggerFactory.getLogger(InvoiceRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Invoice> getInvoicesAndTenderTypesForCustomer(long customerId) {
        Gson gson = new GsonBuilder().create();
        List<Invoice> invoices = new ArrayList<>();

        try {
            try (
                    Connection con = jdbcTemplate.getDataSource().getConnection();
                    //TODO: better DBs like postgress and mySql let you extract json members in the select statement
                    // but because I am using h2 AND jdbc I can't easily extract json data from the query
                    // therefor I chose to insert strings into the DB for the invoice_data column
                    //
                    // further I could have the sql and this function return the map,
                    // I lean towards keeping the data access layer more simple
                    PreparedStatement ps = con.prepareStatement(
                            "SELECT customer_id, invoice_id, invoice_data " +
                                    "FROM invoice " +
                                    "where customer_id = ? " +
                                    "order by invoice_id"); //sorting is nice for testing and predictability and needed for pagination if that was added
                    ) {
                // always use PreparedStatement for SQL injection protection.
                // Even though this is setting a long now more params could get added
                ps.setLong(1, customerId);
                try (ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
                        Invoice invoice = new Invoice(
                                rs.getLong("customer_id"),
                                rs.getLong("invoice_id"),
                                gson.fromJson(rs.getString("invoice_data"), InvoiceData.class)
                        );
                        invoices.add(invoice);
                        log.info(invoice.toString());
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return invoices;
    }
}
