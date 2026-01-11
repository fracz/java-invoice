package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;


    protected Product(String name, BigDecimal price, BigDecimal tax) {

        if(name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        this.name = name;

        if(price == null) {
            throw new IllegalArgumentException("price cannot be null");
        }
        this.price = price;

       if(tax == null) {
           throw new IllegalArgumentException("tax cannot be null");
       }
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
        BigDecimal result = this.price.multiply(this.taxPercent);
        return (BigDecimal)price.add(result);

    }
}
