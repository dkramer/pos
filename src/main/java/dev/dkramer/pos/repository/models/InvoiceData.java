package dev.dkramer.pos.repository.models;

import lombok.Value;

@Value
public class InvoiceData {
    //TODO: should probably be ZonedDateTime
    private String time;

    private TenderDetails tenderDetails;

    //TODO: could be an int? Instructions show it as a String
    private String storeNumber;



}
