package com.project.driver;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.project.model.FinalResponse;
import com.project.model.PurchasedItem;
import com.project.model.services.IOService;
import com.project.model.services.ReceiptService;
import com.project.model.services.TaxService;

public class Driver {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Enter the number of inputs:");
		int repetition = Integer.parseInt(sc.nextLine());
		System.out.println("Enter inputs:");
		
		//taking inputs
		IOService ioService = new IOService();
		List<String> inputList = ioService.takeInput(repetition);
		List<PurchasedItem> items = ioService.getPurchasedItemFromInputString(inputList);
		
		//calculation of taxes
		TaxService taxService = new TaxService();
		items = taxService.calculateTax(items);
		
		//final receipt generation
		ReceiptService receiptService = new ReceiptService();
		FinalResponse response = receiptService.getFinalResponse(items);
		List<String> outputList = receiptService.generateReceipt(response);
		receiptService.printReceipt(outputList);

	}

}
