package repository;

import product.Product;

import java.util.Map;
import java.util.Optional;

public interface ProductsReposit {
    void add(Product product);

    Optional<Product> findProductById(String id);

    Map<String, Product> findAll();

    void delete(Product product);
}
