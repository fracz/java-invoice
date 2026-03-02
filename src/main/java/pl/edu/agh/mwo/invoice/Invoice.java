package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products =
            new HashMap<>();


    public void addProduct(Product product) {

        this.addProduct(product, 1);
    }
    public void addProduct(Product product, Integer quantity) {
        if (quantity<=0 ) {
            throw new IllegalArgumentException("Quantity of products cannot be less or equal 0");
        }
        else if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        this.products.put(product, quantity);
    }


    public BigDecimal getSubtotal() {
        BigDecimal value = BigDecimal.ZERO;
        for (Product product : this.products.keySet()) {
            Integer quantity = this.products.get(product);
            BigDecimal price = product.getPrice();
            price = price.multiply(BigDecimal.valueOf(quantity));
            value = value.add(price);
        }
        return value;

    }

    public BigDecimal getTax() {

        return getTotal().subtract(getSubtotal());

    }

    public BigDecimal getTotal() {
        BigDecimal value = BigDecimal.ZERO;
        for (Product product : this.products.keySet()) {
            Integer quantity = this.products.get(product);
            BigDecimal price = product.getPriceWithTax(); //.subtract(product.getTaxPercent()/100.0);  //.multiply(new BigDecimal("0.01")));
            price = price.multiply(BigDecimal.valueOf(quantity)); //.setScale(2, RoundingMode.HALF_UP);
            System.out.println("price aft round");
            System.out.println(price);
            System.out.println(product);
            value = value.add(price);
        }
        return value;
    }
}
