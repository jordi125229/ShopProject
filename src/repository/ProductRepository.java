package repository;

import file.FileReader;
import file.FileWriter;
import money.Money;
import product.Product;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ProductRepository implements ProductsReposit {
    private Map<String, Product> products;
    private FileReader fileReader;
    private FileWriter fileWriter;

    public ProductRepository() {
        this.products = new HashMap<>();
        this.fileWriter = new FileWriter();
    }

    @Override
    public void add(Product product) {
        products.put(product.getId(), product);
        fileWriter.saveProductToFile(product);
    }

    @Override
    public Optional<Product> findProductById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public Map<String, Product> findAll() {
        if (products.isEmpty()){
            System.out.println("No products");
        }
        return products;
    }

    @Override
    public void delete(Product product) {
        products.remove(product.getId());
    }
}
