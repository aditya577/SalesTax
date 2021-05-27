package com.project.model;

import java.math.BigDecimal;

public class PurchasedItem {
	private String product;
	private BigDecimal price;
	private boolean imported;
	private boolean basicTaxExempt;
	private TaxAmount tax;

	public PurchasedItem() {
	}

	public PurchasedItem(String product, BigDecimal price) {
		this.product = product;
		this.price = price;
	}

	public PurchasedItem(String product, BigDecimal price, boolean imported, boolean basicTaxExempt) {
		this.product = product;
		this.price = price;
		this.imported = imported;
		this.basicTaxExempt = basicTaxExempt;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public BigDecimal getPrice() {
		return price.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	public boolean isBasicTaxExempt() {
		return basicTaxExempt;
	}

	public void setBasicTaxExempt(boolean basicTaxExempt) {
		this.basicTaxExempt = basicTaxExempt;
	}

	public TaxAmount getTax() {
		return tax;
	}

	public void setTax(TaxAmount tax) {
		this.tax = tax;
	}

	public static class TaxAmount {
		private BigDecimal basicTax;
		private BigDecimal importTax;

		public BigDecimal getBasicTax() {
			return basicTax;
		}

		public void setBasicTax(BigDecimal basicTax) {
			this.basicTax = basicTax;
		}

		public BigDecimal getImportTax() {
			return importTax;
		}

		public void setImportTax(BigDecimal importTax) {
			this.importTax = importTax;
		}

	}

}
