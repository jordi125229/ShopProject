package manager;

import client.Client;
import order.Order;
import repository.Cart;
import repository.OrderRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class OrderManager {
    private OrderRepository orderRepository;

    public OrderManager(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order order(Cart cart, Client client, LocalDateTime start) {
        Order order = new Order();
        String date = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        order.setCart(cart);
        order.setClient(client);
        order.setDate(start);
        order.setId("BK-<" + date + counterCreation() + ">");
        order.setTotalPrice(cart.calculateTotalPrice());
        orderRepository.addOrder(order);
        return order;
    }

    public static int counterCreation() {
        Random random = new Random();
        return random.nextInt(1000);
    }
}
