package manager;

import exceptions.NoAvailableId;
import exceptions.NoProductException;
import lombok.AllArgsConstructor;
import money.Money;
import org.apache.commons.lang3.Validate;
import product.Computer;
import product.Electronics;
import product.Product;
import product.Smartphone;
import repository.ProductRepository;

import java.util.Optional;

@AllArgsConstructor
public class ProductManager {
    private final ProductRepository productRepository;

    public Computer createComputer(String id, String name, Money price, int quantity) {
        quantityValidation(quantity);
        idValidation(id, productRepository);
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
        idValidation(id, productRepository);
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
        idValidation(id, productRepository);
        Electronics electronics = Electronics.builder()
                .id(id)
                .name(name)
                .price(price)
                .quantity(quantity).build();
        productRepository.add(electronics);
        return electronics;
    }

    private static void quantityValidation(int quantity) {
        Validate.isTrue(quantity >= 0, "Quantity can't be negative!");
    }

    private static void idValidation(String id, ProductRepository productRepository) {
        Validate.isTrue(!id.isEmpty(), "ID can't be empty!");
        Optional<Product> productById = productRepository.findProductById(id);
        if (productById.isPresent()) {
            throw new NoAvailableId("Id is in used!");
        }
    }

    public void deleteProduct(String id) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new NoProductException("Product not found"));
        productRepository.delete(product);
    }
}
