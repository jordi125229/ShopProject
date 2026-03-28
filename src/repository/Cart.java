package repository;

import exceptions.NoProductException;
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
        if (productsBucket.isEmpty()) {
            throw new NoProductException("No products in the bucket!");
        }
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
}
