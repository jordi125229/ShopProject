package manager;

import client.Client;
import order.Order;
import product.productToCart.ProductToCart;
import repository.Cart;
import repository.OrderRepository;
import threadsExecutor.OrderExecutor;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class OrderManager {
    private OrderRepository orderRepository;
    private CartManager cartManager;
    private OrderExecutor orderExecutor;

    public OrderManager(OrderRepository orderRepository, CartManager cartManager, OrderExecutor orderExecutor) {
        this.orderRepository = orderRepository;
        this.cartManager = cartManager;
        this.orderExecutor = orderExecutor;
    }

    public Order order(Cart cart, Client client, ZonedDateTime start) {
        String date = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<ProductToCart> productsCopy = new ArrayList<>(cart.findAll());
        Order order = new Order(client, productsCopy, start);
        order.setId("BK-<" + date + counterCreation() + ">");
        order.setTotalPrice(cartManager.calculateTotalPrice());
        Future<?> process = orderExecutor.orderProcessing(order);
        try {
            process.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        orderRepository.addOrder(order);
        return order;
    }

    public static int counterCreation() {
        Random random = new Random();
        return random.nextInt(1000);
    }
}
