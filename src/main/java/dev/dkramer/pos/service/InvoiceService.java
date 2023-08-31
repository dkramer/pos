package dev.dkramer.pos.service;

import dev.dkramer.pos.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;


    public Map<Long, String> getInvoicesAndTenderTypesForCustomer(long customerId) {
        return invoiceRepository.getInvoicesAndTenderTypesForCustomer(customerId)
                .stream()
                .collect(
                        Collectors.toMap(
                                invoice -> invoice.getInvoiceId(),
                                invoice -> invoice.getInvoiceData().getTenderDetails().getType()
                        )
                );
    }
}
