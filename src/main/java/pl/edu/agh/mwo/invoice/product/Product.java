package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
	private final String name;

	private final BigDecimal price;

	private final BigDecimal taxPercent;

	protected Product(String name, BigDecimal price, BigDecimal tax) throws RuntimeException{

		if(name == null || name.trim().isEmpty()){
			throw new IllegalArgumentException("poduct name cannot be null");
		}
		this.name = name;
		this.price = price;
		this.taxPercent = tax;
	}

	public String getName() {

		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getTaxPercent() {
		return taxPercent;
	}

	public BigDecimal getPriceWithTax() {
		return this.price.add(this.price.multiply(this.taxPercent));
	}
}
