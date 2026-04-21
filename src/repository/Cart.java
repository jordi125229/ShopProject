package repository;

import exceptions.NoProductException;
import exceptions.NoProductInTheCart;
import product.Product;
import product.ProductToCart;

import java.util.*;

public class Cart implements ICart {
    private Map<String, ProductToCart> productsBucket;

    public Cart() {
        this.productsBucket = new HashMap<>();
    }

    @Override
    public void addProduct(Product product, int quantity) {
        productsBucket.put(product.getId(), new ProductToCart(product, quantity));
    }

    @Override
    public Collection<ProductToCart> findAll() {
        return productsBucket.values();
    }

    public boolean isEmpty() {
        return productsBucket.isEmpty();
    }

    @Override
    public void clear() {
        productsBucket.clear();
    }

    @Override
    public String toString() {
        return "Cart: " +
                "productsBucket=" + productsBucket;
    }
}
