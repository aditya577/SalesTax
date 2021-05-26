package com.project.utils;

import java.math.BigDecimal;
import java.util.List;

import com.project.model.PurchasedItem;
import com.project.model.TaxConstants;

public class Utility {
	public static boolean isImported(String input) {
		if (input == null) {
			return false;
		}
		return input.toLowerCase().contains(TaxConstants.getImportedKeyword());
	}

	public static boolean isBasicTaxExempt(String input) {
		if (input == null) {
			return false;
		}
		boolean basicTaxExempt = false;
		String[] exemptItems = TaxConstants.getBasicTaxExemptString().split(",");
		for (String exemptItem : exemptItems) {
			if (input.toLowerCase().contains(exemptItem.toLowerCase())) {
				basicTaxExempt = true;
				break;
			}
		}
		return basicTaxExempt;
	}

	public static BigDecimal calculateTotalPrice(List<PurchasedItem> items) {
		if (items == null || items.size() <= 0) {
			return null;
		}
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (PurchasedItem item : items) {
			totalPrice = totalPrice.add(getPriceWithTax(item));
		}
		return totalPrice;
	}

	public static BigDecimal calculateTotalTax(List<PurchasedItem> items) {
		if (items == null || items.size() <= 0) {
			return null;
		}
		BigDecimal totalTax = BigDecimal.ZERO;
		for (PurchasedItem item : items) {
			if (item.getTax() != null) {
				if (!item.isBasicTaxExempt() && item.getTax().getBasicTax() != null)
					totalTax = totalTax.add(item.getTax().getBasicTax());
				if (item.isImported() && item.getTax().getImportTax() != null)
					totalTax = totalTax.add(item.getTax().getImportTax());
			}
		}
		return totalTax;
	}

	public static BigDecimal calculateBasicTax(BigDecimal price) {
		BigDecimal basicTax = price.multiply(TaxConstants.getBasicTaxPercentage());
		return roundOff(basicTax);
	}

	public static BigDecimal calculateImportingTax(BigDecimal price) {
		BigDecimal importTax = price.multiply(TaxConstants.getImportDutyTaxPercentage());
		return roundOff(importTax);
	}

	public static BigDecimal roundOff(BigDecimal bigTax) {
		Double tax = bigTax.doubleValue();
		tax = Math.round(tax * 20.0) / 20.0;
		return new BigDecimal(tax);
	}

	public static BigDecimal getPriceWithTax(PurchasedItem item) {
		if (item == null || item.getPrice() == null) {
			return null;
		}
		BigDecimal finalAmt = item.getPrice();
		if (item.getTax() != null) {
			if (item.getTax().getBasicTax() != null)
				finalAmt = finalAmt.add(item.getTax().getBasicTax());
			if (item.getTax().getImportTax() != null)
				finalAmt = finalAmt.add(item.getTax().getImportTax());
		}
		return finalAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
