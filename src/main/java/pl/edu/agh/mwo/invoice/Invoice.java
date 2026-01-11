package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Invoice {
    private final Collection<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product can't be null!");
        }
        products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Number of products can't be negative!");
        }
        IntStream.range(0, quantity).forEach((p) -> addProduct(product));
    }

    private BigDecimal add(Stream<BigDecimal> stream) {
        return stream.reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getSubtotal() {
        return add(products.stream().map(Product::getPrice));
    }

    public BigDecimal getTax() {
        return add(products.stream().map(p -> p.getPrice().multiply(p.getTaxPercent())));

    }

    public BigDecimal getTotal() {
        return add(products.stream().map(Product::getPriceWithTax));
    }
}
