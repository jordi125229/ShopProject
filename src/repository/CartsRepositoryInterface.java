package repository;

import money.Money;
import product.Product;

import java.util.List;

public interface CartsRepositoryInterface {
    void addProduct(Product product);

    List<Product> findAll();

    void clearing();

    Money calculateTotalPrice();
}
