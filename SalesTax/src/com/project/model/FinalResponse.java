package com.project.model;

import java.math.BigDecimal;
import java.util.List;

public class FinalResponse {
	private BigDecimal totalPice;
	private BigDecimal totalTax;
	private List<PurchasedItem> items;

	public FinalResponse(BigDecimal totalPice, BigDecimal totalTax, List<PurchasedItem> items) {
		this.totalPice = totalPice;
		this.totalTax = totalTax;
		this.items = items;
	}

	public BigDecimal getTotalPice() {
		return totalPice.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setTotalPice(BigDecimal totalPice) {
		this.totalPice = totalPice;
	}

	public BigDecimal getTotalTax() {
		return totalTax.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public List<PurchasedItem> getItems() {
		return items;
	}

	public void setItems(List<PurchasedItem> items) {
		this.items = items;
	}
}
