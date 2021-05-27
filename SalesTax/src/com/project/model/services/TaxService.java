package com.project.model.services;

import java.math.BigDecimal;
import java.util.List;

import com.project.model.Constants;
import com.project.model.PurchasedItem;
import com.project.model.PurchasedItem.TaxAmount;

public class TaxService{
	
	public List<PurchasedItem> calculateTax(List<PurchasedItem> items) {
		if (items == null || items.size() <= 0) {
			return null;
		}
		for (PurchasedItem item : items) {
			TaxAmount tax = new TaxAmount();
			if (!item.isBasicTaxExempt()) {
				BigDecimal basicTax = calculateBasicTax(item.getPrice());
				tax.setBasicTax(basicTax);
			}
			if (item.isImported()) {
				BigDecimal importTax = calculateImportTax(item.getPrice());
				tax.setImportTax(importTax);
			}
			item.setTax(tax);
		}
		return items;
	}

	public BigDecimal calculateBasicTax(BigDecimal price) {
		BigDecimal basicTax = price.multiply(Constants.BASIC_TAX_PERCENTAGE);
		return roundOff(basicTax);
	}

	public BigDecimal calculateImportTax(BigDecimal price) {
		BigDecimal importTax = price.multiply(Constants.IMPORT_DUTY_PERCENTAGE);
		return roundOff(importTax);
	}

	public BigDecimal roundOff(BigDecimal bigTax) {
		Double tax = bigTax.doubleValue();
		tax = Math.round(tax * 20.0) / 20.0;
		return new BigDecimal(tax);
	}
}
