package com.project.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.project.model.services.IOService;
import com.project.model.services.TaxService;

class SalesTaxTest {
	private IOService ioService = new IOService();
	private TaxService taxService = new TaxService();

	@Test
	void whenProductIsImportedThenGivesTrue() {
		String product = "1 imported book at 11.99";
		assertEquals(true, ioService.isImported(product));
	}

	@Test
	void whenProductIsNotImportedThenGivesTrue() {
		String product = "1 book at 11.99";
		assertEquals(false, ioService.isImported(product));
	}

	@Test
	void whenExemptedBasicTaxThenGivesTrue() {
		String product = "1 imported book at 11.99";
		assertEquals(true, ioService.isImported(product));
	}

	@Test
	void whenNotExemptedBasicTaxThenGivesTrue() {
		String product = "1 mobile at 238.90";
		assertEquals(false, ioService.isImported(product));
	}

	@Test
	void testTenPercentageBasicTax() {
		BigDecimal price = new BigDecimal(10.00);
		BigDecimal tax = new BigDecimal(1.00);
		assertEquals(tax, taxService.calculateBasicTax(price));
	}

	@Test
	void testFivePercentageImportTax() {
		BigDecimal price = new BigDecimal(20.00);
		BigDecimal tax = new BigDecimal(1.00);
		assertEquals(tax, taxService.calculateImportTax(price));
	}

	@Test
	void testRoundingOff() {
		BigDecimal originalVal = new BigDecimal(1.83);
		BigDecimal roundedVal = new BigDecimal(1.85);
		assertEquals(roundedVal, taxService.roundOff(originalVal));
	}
}
