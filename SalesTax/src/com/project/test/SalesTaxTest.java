package com.project.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.project.utils.Utility;

class SalesTaxTest {

	@Test
	void whenProductIsImportedThenGivesTrue() {
		String product = "1 imported book at 11.99";
		assertEquals(true, Utility.isImported(product));
	}

	@Test
	void whenProductIsNotImportedThenGivesTrue() {
		String product = "1 book at 11.99";
		assertEquals(false, Utility.isImported(product));
	}

	@Test
	void whenExemptedBasicTaxThenGivesTrue() {
		String product = "1 imported book at 11.99";
		assertEquals(true, Utility.isImported(product));
	}

	@Test
	void whenNotExemptedBasicTaxThenGivesTrue() {
		String product = "1 mobile at 238.90";
		assertEquals(false, Utility.isImported(product));
	}

	@Test
	void testTenPercentageBasicTax() {
		BigDecimal price = new BigDecimal(10.00);
		BigDecimal tax = new BigDecimal(1.00);
		assertEquals(tax, Utility.calculateBasicTax(price));
	}

	@Test
	void testFivePercentageImportTax() {
		BigDecimal price = new BigDecimal(20.00);
		BigDecimal tax = new BigDecimal(1.00);
		assertEquals(tax, Utility.calculateImportingTax(price));
	}

	@Test
	void testRoundingOff() {
		BigDecimal originalVal = new BigDecimal(1.83);
		BigDecimal roundedVal = new BigDecimal(1.85);
		assertEquals(roundedVal, Utility.roundOff(originalVal));
	}
}
