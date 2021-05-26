package com.project.driver;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.project.model.FinalResponse;
import com.project.model.PurchasedItem;
import com.project.operations.Operations;

public class Driver {

	static Operations ops = new Operations();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Enter the number of inputs:");
		int repetition = Integer.parseInt(sc.nextLine());
		System.out.println("Enter inputs:");
		ops.initiate(repetition);
		List<PurchasedItem> items = ops.getPurchasedItemFromInputString();
		items = ops.calculateTax(items);
		FinalResponse response = ops.getFinalResponse(items);
		List<String> outputList = ops.generateReceipt(response);
		printReceipt(outputList);

	}

	public static void printReceipt(List<String> outputList) {
		if (outputList == null || outputList.size() <= 0)
			System.out.println("Empty output list");
		System.out.println("==== receipt ====");
		for (String output : outputList) {
			System.out.println(output);
		}
	}

}
