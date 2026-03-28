package manager;

import money.Money;
import product.Computer;
import product.Product;
import product.Smartphone;
import repository.ProductRepository;

public class ProductManager {
    private final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void updateProduct(String id, String name, Money price, int quantity) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
    }

    public Computer createComputer(String id, String name, Money price, int quantity) {
        Computer computer = new Computer(id, name, price, quantity);
        productRepository.add(computer);
        return computer;
    }

    public Smartphone createSmartfon(String id, String name, Money price, int quantity) {
        Smartphone smartfon = new Smartphone(id, name, price, quantity);
        productRepository.add(smartfon);
        return smartfon;
    }

    public void productDeleting(String id) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        productRepository.delete(product);
    }
}
