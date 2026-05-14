package test;

import client.Client;
import manager.CartManager;
import manager.OrderManager;
import manager.ProductManager;
import money.Money;
import repository.Cart;
import repository.OrderRepository;
import repository.ProductRepository;
import threadsExecutor.OrderExecutor;

import java.time.ZonedDateTime;

public class ExecutorServiceTest {
    public static void main(String[] args) {
        ProductRepository repository = new ProductRepository();
        ProductManager productManager = new ProductManager(repository);
        OrderRepository orderRepository = new OrderRepository();
        Cart cart = new Cart();
        OrderExecutor orderExecutor = new OrderExecutor();
        CartManager cartManager = new CartManager(cart, repository);
        OrderManager orderManager = new OrderManager(orderRepository, cartManager, orderExecutor);
        productManager.createSmartphone("001", "samsung", Money.of("4600"), 10);
        Client client = new Client("Piotr", "Nowak", "012310101");

        for (int i = 0; i < 10; i++) {
            Cart carts = new Cart();
            CartManager cartManagerTester = new CartManager(carts, repository);
            cartManagerTester.addProductToCart("001", 1);
            orderManager.order(carts, client, ZonedDateTime.now());
        }
        orderExecutor.shutdown();
    }
}

