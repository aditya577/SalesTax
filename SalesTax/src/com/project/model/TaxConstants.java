package com.project.model;

import java.math.BigDecimal;

public class TaxConstants {
	private final BigDecimal BASIC_TAX_PERCENTAGE = new BigDecimal(0.10); // 10%
	private final BigDecimal IMPORT_DUTY_PERCENTAGE = new BigDecimal(0.05); // 5%
	private final String BASIC_TAX_EXEMPT_ITEMS_KEYWORD = "book,chocolate,pill";
	private final String IMPORTED_KEYWORD = "imported";

	public static BigDecimal getBasicTaxPercentage() {
		return new TaxConstants().BASIC_TAX_PERCENTAGE;
	}

	public static BigDecimal getImportDutyTaxPercentage() {
		return new TaxConstants().IMPORT_DUTY_PERCENTAGE;
	}

	public static String getBasicTaxExemptString() {
		return new TaxConstants().BASIC_TAX_EXEMPT_ITEMS_KEYWORD;
	}

	public static String getImportedKeyword() {
		return new TaxConstants().IMPORTED_KEYWORD;
	}
}
