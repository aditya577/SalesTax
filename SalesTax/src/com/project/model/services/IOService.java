package com.project.model.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.model.Constants;
import com.project.model.PurchasedItem;

public class IOService{
	private List<String> inputList;
	
	public List<String> takeInput(int repetition) {
		inputList = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String input = scanner.nextLine();
			inputList.add(input);

			repetition--;
			if (repetition == 0) {
				break;
			}
		}
		scanner.close();
		return inputList;
	}
	
	public List<PurchasedItem> getPurchasedItemFromInputString(List<String> inputList) {
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
			boolean imported = isImported(input);
			boolean basicTaxExempt = isBasicTaxExempt(input);
			PurchasedItem item = new PurchasedItem(product, price, imported, basicTaxExempt);
			items.add(item);
		}
		return items;
	}
	
	public boolean isImported(String input) {
		if (input == null) {
			return false;
		}
		return input.toLowerCase().contains(Constants.IMPORTED_KEYWORD);
	}
	
	public boolean isBasicTaxExempt(String input) {
		if (input == null) {
			return false;
		}
		boolean basicTaxExempt = false;
		String[] exemptItems = Constants.BASIC_TAX_EXEMPT_ITEM_KEYWORDS.split(",");
		for (String exemptItem : exemptItems) {
			if (input.toLowerCase().contains(exemptItem.toLowerCase())) {
				basicTaxExempt = true;
				break;
			}
		}
		return basicTaxExempt;
	}

}
