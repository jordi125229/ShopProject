package repository;

import product.Product;

import java.util.List;
import java.util.Optional;

public interface CartsReposit {
    void addProduct(Product product);

    List<Product> findAll();

    void order(Product product);

    void clearing();
}
