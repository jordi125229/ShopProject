package manager;

import client.Client;
import order.Order;
import product.ProductToCart;
import repository.Cart;
import repository.OrderRepository;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderManager {
    private OrderRepository orderRepository;
    private CartManager cartManager;

    public OrderManager(OrderRepository orderRepository, CartManager cartManager) {
        this.orderRepository = orderRepository;
        this.cartManager = cartManager;
    }

    public Order order(Cart cart, Client client, ZonedDateTime start) {
        String date = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<ProductToCart> productsCopy = new ArrayList<>(cart.findAll());
        Order order = new Order(client, productsCopy, start);
        order.setId("BK-<" + date + counterCreation() + ">");
        order.setTotalPrice(cartManager.calculateTotalPrice());
        orderRepository.addOrder(order);
        return order;
    }

    public static int counterCreation() {
        Random random = new Random();
        return random.nextInt(1000);
    }
}
