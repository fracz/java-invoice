package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
	private final String name;

	private final BigDecimal price;

	private final BigDecimal taxPercent;


	protected Product(String name, BigDecimal price, BigDecimal tax) {
		if (name == null ){
			throw new IllegalArgumentException("Product name cannot be null.");
		}
		if (name == ""){
			throw new IllegalArgumentException("Product name cannot be name.");
		}
			this.name = name;
			this.price = price;
			this.taxPercent = tax;

	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public BigDecimal getTaxPercent() {
		return this.taxPercent;
	}

	public BigDecimal getPriceWithTax() {
		return this.price.add(price.multiply(taxPercent));
	}
}
