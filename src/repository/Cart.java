package repository;

import exceptions.NoProductException;
import exceptions.NoProductInTheCart;
import money.Money;
import product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart implements CartsReposit {
    private List<Product> productsBucket;

    public Cart() {
        this.productsBucket = new ArrayList<>();
    }

    @Override
    public void addProduct(Product product) {
        if (product.getQuantity() <= 0){
            throw new NoProductException("No products available!");
        }
        productsBucket.add(product);
    }

    @Override
    public List<Product> findAll() {
        return productsBucket;
    }

    @Override
    public void clearing() {
        productsBucket.clear();
    }

    @Override
    public Money calculateTotalPrice() {
        Money sum = new Money(BigDecimal.ZERO);
        for (Product product : productsBucket) {
            sum = sum.add(product.getPrice().multiply(product.getQuantity()));
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Cart: " +
                "productsBucket=" + productsBucket;
    }

    public String serialize() {
        return productsBucket.stream()
                .map(p -> p.getName() + "," + p.getPrice() + "," + p.getQuantity())
                .reduce((a, b) -> a + b)
                .orElse("");
    }
}
