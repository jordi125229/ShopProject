package repository;

import money.Money;
import product.Product;
import product.productToCart.ProductToCart;

import java.util.Collection;
import java.util.List;

public interface ICartsRepository {
    void addProduct(Product product, int quantity);

    Collection<ProductToCart> findAll();

    void clearing();
}
