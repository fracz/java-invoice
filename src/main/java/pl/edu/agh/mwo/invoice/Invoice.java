package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	//private Collection<Product> products;
	private Map<Product, Integer> products = new LinkedHashMap<>();

	public void addProduct(Product product) {
		products.put(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("Cannot add product with negative or zero quantity");
		}
		products.put(product, quantity);
	}

	public BigDecimal getSubtotal() {
		BigDecimal subTotal = BigDecimal.ZERO;
		for (Product product : this.products.keySet()) {
			Integer quantity = this.products.get(product);
			subTotal = subTotal.add(product.getPrice().multiply(new BigDecimal(quantity)));
		}
		return subTotal;
	}

	public BigDecimal getTax() {
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal percent = new BigDecimal("10");
		for (Product product : this.products.keySet()) {
			tax = tax.add(product.getTaxPercent()).multiply(percent);
		}
		return tax;
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (Product product : this.products.keySet()) {
			Integer quantity = this.products.get(product);
			total = total.add(product.getPriceWithTax().multiply(new BigDecimal(quantity)));
		}
		return total;
	}
}
