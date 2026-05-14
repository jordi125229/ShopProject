package manager;

import exceptions.NegativeQuantityException;
import exceptions.NoProductException;
import money.Money;
import product.Computer;
import product.Electronics;
import product.Product;
import product.Smartphone;
import repository.ProductRepository;

public class ProductManager {
    private final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Computer createComputer(String id, String name, Money price, int quantity) {
        quantityValidation(quantity);
        Computer computer = new Computer(id, name, price, quantity);
        productRepository.add(computer);
        return computer;
    }

    public Smartphone createSmartphone(String id, String name, Money price, int quantity) {
        quantityValidation(quantity);
        Smartphone smartphone = new Smartphone(id, name, price, quantity);
        productRepository.add(smartphone);
        return smartphone;
    }

    public Electronics createElectronic(String id, String name, Money price, int quantity) {
        quantityValidation(quantity);
        Electronics electronics = new Electronics(id, name, price, quantity);
        productRepository.add(electronics);
        return electronics;
    }

    private static void quantityValidation(int quantity) {
        if (quantity < 0) {
            throw new NegativeQuantityException("Quantity can't be negative!");
        }
    }

    public void productDeleting(String id) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new NoProductException("Product not found"));
        productRepository.delete(product);
    }
}
