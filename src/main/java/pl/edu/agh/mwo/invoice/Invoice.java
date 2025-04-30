package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private final Map<Product, Integer> products = new LinkedHashMap<>();
    private static int nextNumber = 0;
    private final int number = ++nextNumber;

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            BigDecimal quantity = BigDecimal.valueOf(entry.getValue());
            totalNet = totalNet.add(entry.getKey().getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            BigDecimal quantity = BigDecimal.valueOf(entry.getValue());
            totalGross = totalGross.add(entry.getKey().getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getNumber() {
        return number;
    }

    public String getInvoiceAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Faktura: ").append(number).append("\n");


        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();

            sb.append(product.getName())
                    .append(", ").append(quantity)
                    .append(" szt., ").append(product.getPrice().setScale(2)).append("\n");
        }

        sb.append("Liczba pozycji: ").append(products.size());

        return sb.toString();
    }
}