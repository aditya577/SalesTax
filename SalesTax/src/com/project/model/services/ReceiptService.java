package com.project.model.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.project.model.FinalResponse;
import com.project.model.PurchasedItem;

public class ReceiptService{
	
	public BigDecimal calculateTotalPrice(List<PurchasedItem> items) {
		if (items == null || items.size() <= 0) {
			return null;
		}
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (PurchasedItem item : items) {
			totalPrice = totalPrice.add(getPriceWithTax(item));
		}
		return totalPrice;
	}

	public BigDecimal calculateTotalTax(List<PurchasedItem> items) {
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
	
	public BigDecimal getPriceWithTax(PurchasedItem item) {
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
	
	public FinalResponse getFinalResponse(List<PurchasedItem> items) {
		BigDecimal totalPrice = calculateTotalPrice(items);
		BigDecimal totalTax = calculateTotalTax(items);

		FinalResponse response = new FinalResponse(totalPrice, totalTax, items);
		return response;
	}


	public List<String> generateReceipt(FinalResponse response) {
		List<String> receipt = new ArrayList<String>();
		for (PurchasedItem item : response.getItems()) {
			BigDecimal finalItemPrice = getPriceWithTax(item);
			String finalString = item.getProduct() + ": " + finalItemPrice;
			receipt.add(finalString);
		}
		receipt.add("Sales Taxes: " + response.getTotalTax());
		receipt.add("Total: " + response.getTotalPice());
		List<String> outputList = new ArrayList<String>();
		outputList.addAll(receipt);
		return outputList;
	}
	
	public void printReceipt(List<String> outputList) {
		if (outputList == null || outputList.size() <= 0)
			System.out.println("Empty output list");
		System.out.println("==== receipt ====");
		for (String output : outputList) {
			System.out.println(output);
		}
	}
}
