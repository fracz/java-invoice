package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
	private final String name;

	private final BigDecimal price;

	private final BigDecimal taxPercent;

	protected Product(String name, BigDecimal price, BigDecimal tax) {

		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException();
		} else {
			this.name = name;
		}

		if (price == null) {
			throw new IllegalArgumentException();
		}

		BigDecimal ZERO = new BigDecimal("0");
		int comparison = ZERO.compareTo(price);

		if (comparison == 1) {
			throw new IllegalArgumentException();
		} else {
			this.price = price;
		}

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
		return price.multiply(taxPercent).add(price);
	}
}
