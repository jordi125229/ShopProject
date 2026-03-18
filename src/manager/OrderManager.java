package manager;

import client.Client;
import order.Order;
import repository.Cart;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class OrderManager {
    private Cart cart;

    public Order order(Cart cart, Client client, LocalDateTime start, LocalDateTime end) {
        Order order = new Order();
        String date = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        order.setCart(cart);
        order.setClient(client);
        order.setStart(start);
        order.setEnd(end);
        order.setId("BK-<" + date + counterCreation() + ">");
        order.setTotalPrice(cart.calculateTotalPrice());
        return order;
    }

    public static int counterCreation() {
        Random random = new Random();
        return random.nextInt(1000);
    }
}
