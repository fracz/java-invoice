package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.Product;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Invoice {
    private final Map<Product, Integer> products = new LinkedHashMap<>();
    private static int nextNumber = 0;
    private final int number = ++nextNumber;

    public void addProduct(Product product) {
        int actualCount = products.getOrDefault(product, 0);
        addProduct(product, actualCount + 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getNumber() {
        return number;
    }

    public String getProductsList() {
        StringBuilder productsList = new StringBuilder("Invoice: " + getNumber()).append("\n");
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            productsList.append("%s, %d, %s\n".formatted(product.getName(), entry.getValue(), product.getPrice()));
        }
        productsList.append("-----\n");
        productsList.append("Liczba pozycji: ").append(products.values().stream().mapToInt(e -> e).sum()).append("\n");
        return productsList.toString();
    }
}
