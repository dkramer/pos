package dev.dkramer.pos;

import dev.dkramer.pos.api.InvoiceResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;


@SpringBootTest
class PosApplicationTests {

	@Autowired
	private InvoiceResource invoiceResource;

	@Test
	void contextLoads() {
	}

	@Test
	void getInvoicesAndTenderTypesForCustomer() {
		Map<Long, String> results = invoiceResource.getInvoicesAndTenderTypesForCustomer(2);
		assert results.get(55L).equals("cash");
		assert results.get(56L).equals("credit");
		assert results.size() == 2;

		results = invoiceResource.getInvoicesAndTenderTypesForCustomer(1);
		assert results.get(54L).equals("cash");
		assert results.size() == 1;

		results = invoiceResource.getInvoicesAndTenderTypesForCustomer(0);
		assert results.size() == 0;

		results = invoiceResource.getInvoicesAndTenderTypesForCustomer(-5);
		assert results.size() == 0;
	}


}
