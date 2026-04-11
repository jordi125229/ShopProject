package repository;

import exceptions.NoProductException;
import file.FileReader;
import file.FileWriter;
import money.Money;
import product.Product;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ProductRepository implements IProductsRepository {
    private final Map<String, Product> products;

    public ProductRepository() {
        this.products = new HashMap<>();
    }

    @Override
    public void add(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Optional<Product> findProductById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public Map<String, Product> findAll() {
        return products;
    }

    @Override
    public void delete(Product product) {
        products.remove(product.getId());
    }
}
