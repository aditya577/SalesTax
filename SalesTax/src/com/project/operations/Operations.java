package com.project.operations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.model.FinalResponse;
import com.project.model.PurchasedItem;
import com.project.model.PurchasedItem.TaxAmount;
import com.project.utils.Utility;

public class Operations {
	private Scanner scanner = new Scanner(System.in);
	private static List<String> inputList;
	private static List<String> outputList;

	public List<String> initiate(int repetition) {
		inputList = new ArrayList<String>();
		while (true) {
			String input = scanner.nextLine();
			inputList.add(input);

			repetition--;
			if (repetition == 0) {
				break;
			}
		}
		return inputList;
	}

	public List<PurchasedItem> getPurchasedItemFromInputString() {
		if (inputList == null || inputList.size() <= 0) {
			return null;
		}
		List<PurchasedItem> items = new ArrayList<PurchasedItem>();
		for (String input : inputList) {
			String[] segment = input.split(" at ");
			String product = segment[0].trim();
			BigDecimal price = BigDecimal.ZERO;
			try {
				price = new BigDecimal(segment[1].trim());
			} catch (Exception e) {
				System.out.println("currency format is wrong");
				e.printStackTrace();
				System.exit(0);
			}
			boolean imported = Utility.isImported(input);
			boolean basicTaxExempt = Utility.isBasicTaxExempt(input);
			PurchasedItem item = new PurchasedItem(product, price, imported, basicTaxExempt);
			items.add(item);
		}
		return items;
	}

	public List<PurchasedItem> calculateTax(List<PurchasedItem> items) {
		if (items == null || items.size() <= 0) {
			return null;
		}
		for (PurchasedItem item : items) {
			TaxAmount tax = new TaxAmount();
			if (!item.isBasicTaxExempt()) {
				BigDecimal basicTax = Utility.calculateBasicTax(item.getPrice());
				tax.setBasicTax(basicTax);
			}
			if (item.isImported()) {
				BigDecimal importTax = Utility.calculateImportingTax(item.getPrice());
				tax.setImportTax(importTax);
			}
			item.setTax(tax);
		}
		return items;
	}

	public FinalResponse getFinalResponse(List<PurchasedItem> items) {
		BigDecimal totalPrice = Utility.calculateTotalPrice(items);
		BigDecimal totalTax = Utility.calculateTotalTax(items);

		FinalResponse response = new FinalResponse(totalPrice, totalTax, items);
		return response;
	}

	public List<String> generateReceipt(FinalResponse response) {
		List<String> receipt = new ArrayList<String>();
		for (PurchasedItem item : response.getItems()) {
			BigDecimal finalItemPrice = Utility.getPriceWithTax(item);
			String finalString = item.getProduct() + ": " + finalItemPrice;
			receipt.add(finalString);
		}
		// receipt.add("-----------------");
		receipt.add("Sales Taxes: " + response.getTotalTax());
		receipt.add("Total: " + response.getTotalPice());
		outputList = new ArrayList<String>();
		outputList.addAll(receipt);
		return outputList;
	}

}
