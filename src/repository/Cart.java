package repository;

import exceptions.NoProductException;
import product.Product;
import product.productToCart.ProductToCart;

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
        if (productsBucket.isEmpty()) {
            throw new NoProductException("Cart is empty!");
        }
        return productsBucket.values();
    }

    @Override
    public void clearing() {
        productsBucket.clear();
    }

    @Override
    public String toString() {
        return "Cart: " +
                "productsBucket=" + productsBucket;
    }
}
