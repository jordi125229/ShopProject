package manager;

import exceptions.NoProductException;
import product.Product;
import repository.Cart;
import repository.ProductRepository;

import java.util.List;

public class CartManager {
    Cart cartRepository;
    ProductRepository productRepository;

    public CartManager(Cart cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public void printCarts(){
        List<Product> all = cartRepository.findAll();
        System.out.println(all);
    }

    public void addProductToCart(String id, int quantity) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new IllegalArgumentException("Product wasn't found!"));
        if (product.getQuantity() < quantity) {
            throw new NoProductException("No enough product in the warehouse!");
        }
        product.setQuantity(product.getQuantity() - quantity);
        Product productToCart = new Product(product.getId(), product.getName(), product.getPrice(), quantity);
        cartRepository.addProduct(productToCart);
    }
}
