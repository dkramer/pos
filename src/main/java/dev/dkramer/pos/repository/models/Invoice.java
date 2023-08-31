package dev.dkramer.pos.repository.models;

import lombok.Value;

@Value
public class Invoice {

    private long customerId;

    private long invoiceId;

    private InvoiceData invoiceData;
}
