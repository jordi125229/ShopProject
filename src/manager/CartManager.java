package manager;

import product.Product;
import repository.Cart;
import repository.ProductRepository;

public class CartManager {
    Cart cartRepository;
    ProductRepository productRepository;

    public CartManager(Cart cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public void addProductToCart(String id) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new IllegalArgumentException("Product wasn't found!"));
        cartRepository.addProduct(product);
    }
}
