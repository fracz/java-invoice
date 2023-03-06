package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;
    private static final BigDecimal MINIMUM_PRICE = new BigDecimal(0);

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        if(name == null || name.isEmpty() || price == null || price.compareTo(Product.MINIMUM_PRICE) < 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.price = price;
        this.taxPercent = tax;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getTaxPercent() {
        return this.taxPercent;
    }

    public BigDecimal getPriceWithTax() {
        BigDecimal divisor = new BigDecimal(100);
        return this.getPrice().add(this.getPrice().multiply(this.getTaxPercent()));
    }
}
