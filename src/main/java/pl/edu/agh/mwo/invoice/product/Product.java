package pl.edu.agh.mwo.invoice.product;

import org.hamcrest.Matchers;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        if (name == null | name.equals("") ) {throw new IllegalArgumentException("Empty name");}
        this.name = name;
        if (price == null | price.compareTo(new BigDecimal(0)) == -1) { throw new IllegalArgumentException("Price null or less than 0");}
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
        return price.multiply(taxPercent).add(price);
    }
}
