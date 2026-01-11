package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product is null, incorect.");
        }
        this.products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity equols 0, incorect.");
        }
        for (int i = 0; i < quantity; i++) {
            this.addProduct(product);
        }
    }

    public BigDecimal getSubtotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : products) {
            sum = sum.add(product.getPrice());
        }
        return sum;
    }

    public BigDecimal getTax() {
        BigDecimal taxSum = BigDecimal.ZERO;
        for (Product product : products) {
            // Podatek = cena netto * procent podatku
            BigDecimal productTax = product.getPrice().multiply(product.getTaxPercent());
            taxSum = taxSum.add(productTax);
        }
        return taxSum;
    }

    public BigDecimal getTotal() {
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Product product : products) {
            totalSum = totalSum.add(product.getPriceWithTax());
        }
        return totalSum;
    }
}
