package manager;

import exceptions.NegativeQuantityException;
import exceptions.NoProductException;
import lombok.AllArgsConstructor;
import money.Money;
import product.Computer;
import product.Electronics;
import product.Product;
import product.Smartphone;
import repository.ProductRepository;

@AllArgsConstructor
public class ProductManager {
    private final ProductRepository productRepository;

    public Computer createComputer(String id, String name, Money price, int quantity) {
        quantityValidation(quantity);
        Computer computer = Computer.builder()
                .id(id)
                .name(name)
                .price(price)
                .quantity(quantity)
                .build();
        productRepository.add(computer);
        return computer;
    }

    public Smartphone createSmartphone(String id, String name, Money price, int quantity) {
        quantityValidation(quantity);
        Smartphone smartphone = Smartphone.builder()
                .id(id)
                .name(name)
                .price(price)
                .quantity(quantity).build();
        productRepository.add(smartphone);
        return smartphone;
    }

    public Electronics createElectronic(String id, String name, Money price, int quantity) {
        quantityValidation(quantity);
        Electronics electronics = Electronics.builder()
                .id(id)
                .name(name)
                .price(price)
                .quantity(quantity).build();
        productRepository.add(electronics);
        return electronics;
    }

    private static void quantityValidation(int quantity) {
        if (quantity < 0) {
            throw new NegativeQuantityException("Quantity can't be negative!");
        }
    }

    public void deleteProduct(String id) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new NoProductException("Product not found"));
        productRepository.delete(product);
    }
}
