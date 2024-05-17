package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.*;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products;

    public Invoice() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0 || product == null) {
            throw new IllegalArgumentException("Ilość musi być większa niż 0");
        } else {
            products.put(product, quantity);
        }
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> prod : products.entrySet()) {
            Product product = prod.getKey();
            Integer quantity = prod.getValue();
            BigDecimal price = product.getPrice().multiply(new BigDecimal(quantity));
            subtotal = subtotal.add(price);
        }
        return subtotal;
    }

    public BigDecimal getTax() {
        BigDecimal totalTax = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> prod : products.entrySet()) {
            Product product = prod.getKey();
            Integer quantity = prod.getValue();
            BigDecimal tax = product.getPriceWithTax().subtract(product.getPrice()).multiply(new BigDecimal(quantity));
            totalTax = totalTax.add(tax);
        }
        return totalTax;
    }

    public BigDecimal getTotal() {
        return getSubtotal().add(getTax());
    }
}
