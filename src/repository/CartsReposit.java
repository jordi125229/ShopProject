package repository;

import money.Money;
import product.Product;

import java.util.List;
import java.util.Optional;

public interface CartsReposit {
    void addProduct(Product product);

    List<Product> findAll();

    void clearing();

    Money calculateTotalPrice();
}
