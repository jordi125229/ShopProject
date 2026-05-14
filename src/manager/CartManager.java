package manager;

import exceptions.NoProductException;
import money.Money;
import product.Product;
import product.ProductToCart;
import repository.Cart;
import repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Collection;

public class CartManager {
    private final Cart cartRepository;
    private final ProductRepository productRepository;

    public CartManager(Cart cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public void addProductToCart(String id, int quantity) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new IllegalArgumentException("Product wasn't found!"));
        if (product.getQuantity() < quantity) {
            throw new NoProductException("No enough product in the warehouse!");
        }
        product.setQuantity(product.getQuantity() - quantity);
        cartRepository.addProduct(product, quantity);
    }

    public Money calculateTotalPrice() {
        Money sum = new Money(BigDecimal.ZERO);
        try {
            Collection<ProductToCart> items = cartRepository.findAll();
            for (ProductToCart item : items) {
                sum = sum.add(item.getProduct().getPrice().multiply(item.getQuantity()));
            }
        } catch (NoProductException e) {
            return new Money(BigDecimal.ZERO);
        }
        return sum;
    }
}
