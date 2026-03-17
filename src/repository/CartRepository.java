package repository;

import product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartRepository implements CartsReposit {
    private List<Product> productsBucket;

    public CartRepository() {
        this.productsBucket = new ArrayList<>();
    }

    @Override
    public void addProduct(Product product) {
        productsBucket.add(product);
    }

    @Override
    public List<Product> findAll() {
        if (productsBucket.isEmpty()) {
            System.out.println("No products in the bucket!");
        }
        return productsBucket;
    }

    @Override
    public void order(Product product) {

    }

    @Override
    public void clearing() {
        productsBucket.clear();
    }
}
