package repository;

import product.Product;
import product.ProductToCart;

import java.util.Collection;

public interface ICart {
    void addProduct(Product product, int quantity);

    Collection<ProductToCart> findAll();

    void clearing();
}
