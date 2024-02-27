package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    //private Map<Product, Integer> products;
    private Collection<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException();
        }
        this.products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException();
        }
        for (var i = 0; i < quantity; i++) {
            products.add(product);
        }
    }

    public BigDecimal getNetPrice() {
        var sumNet = BigDecimal.ZERO;
        for (var product : products) {
            sumNet = sumNet.add(product.getPrice());
        }
        return sumNet;
    }

    public BigDecimal getTax() {
        var sumTax = BigDecimal.ZERO;
        for (var product : products) {
            sumTax = sumTax.add(product.getTaxPercent().multiply(product.getPrice()));
        }
        return sumTax;
    }

    public BigDecimal getGrossPrice() {
        var sumGross = BigDecimal.ZERO;
        for (var product : products) {
            sumGross = sumGross.add(product.getPriceWithTax());
        }
        return sumGross;
    }

    public Collection<Product> getProducts() {
        return products;
    }
}
