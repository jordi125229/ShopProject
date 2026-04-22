package test;

import client.Client;
import manager.CartManager;
import manager.OrderManager;
import manager.ProductManager;
import money.Money;
import order.Order;
import product.Product;
import product.ProductToCart;
import repository.Cart;
import repository.OrderRepository;
import repository.ProductRepository;
import threadsExecutor.Executor;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DiscountsTest {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        OrderRepository orderRepository = new OrderRepository();
        ProductManager productManager = new ProductManager(productRepository);
        Cart cart = new Cart();
        CartManager cartManager = new CartManager(cart, productRepository);
        Executor executor = new Executor(3);
        OrderManager orderManager = new OrderManager(orderRepository, cartManager, executor);
        discountTest(productManager, productRepository, cartManager, cart, orderManager, executor);
    }

    private static void discountTest(ProductManager productManager, ProductRepository productRepository, CartManager cartManager, Cart cart, OrderManager orderManager, Executor executor) {
        productManager.createSmartphone("0012", "Iphone 15", Money.of("5000"), 10);
        Client testClient = new Client("Jakub", "Nowak", "02930194810");
        Map<String, Product> allProducts = productRepository.findAll();
        System.out.println(allProducts);
        cartManager.addProductToCart("0012", 4);
        System.out.println(allProducts);
        Collection<ProductToCart> allProductsInCart = cart.findAll();
        System.out.println(allProductsInCart);
        Order order;
        try {
            order = orderManager.order(cart, testClient, ZonedDateTime.now()).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(order);
        executor.shutdown();
    }
}
