package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.stream.Stream;

public class Invoice {
//    private final Collection<Product> products = new ArrayList<>();

    HashMap<Product, Integer> products = new HashMap<>();


    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product can't be null!");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Number of products can't be negative!");
        }
        products.put(product, quantity);
    }

    private BigDecimal reduce(Stream<BigDecimal> stream) {
        return stream.reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getSubtotal() {

        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal price = product.getPrice();
            BigDecimal taxPercent = product.getTaxPercent();
            sum.add(price.multiply(taxPercent));
        }
        return sum;
//        return add(products.keySet().stream()
//                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(products.get(p)))));
    }

    public BigDecimal getTax() {
        return getTotal().subtract(getSubtotal());
    }

    public BigDecimal getTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal priceWithTax = product.getPriceWithTax();
            BigDecimal taxPercent = product.getTaxPercent();
            sum = sum.add(priceWithTax.multiply(taxPercent));
        }
        return sum;

//        return add(products.keySet().stream()
//                .map(p -> p.getTaxPercent().multiply(BigDecimal.valueOf(products.get(p)))
//                )
//        );
    }
}
