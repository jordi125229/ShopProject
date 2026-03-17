package manager;

import product.Product;
import repository.CartRepository;
import repository.ProductRepository;

public class CartManager {
    CartRepository cartRepository;
    ProductRepository productRepository;

    public CartManager(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public void addProductToCart(String id) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new IllegalArgumentException("Product wasn't found!"));
        cartRepository.addProduct(product);
    }
}
