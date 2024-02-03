package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.util.Calendar;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        if (name == null) {
            throw new IllegalArgumentException("The name cannot be null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty");
        }
        if (price == null) {
            throw new IllegalArgumentException("The price cannot be null");
        }
        if (price.doubleValue() < 0) {
            throw new IllegalArgumentException("The price cannot be negative");
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
        BigDecimal priceWithTax = price.multiply(taxPercent).add(price);
        return priceWithTax;
    }
}
