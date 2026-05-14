package manager;

import client.Client;
import lombok.AllArgsConstructor;
import order.Order;
import product.ProductToCart;
import repository.Cart;
import repository.OrderRepository;
import threadsExecutor.Executor;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

@AllArgsConstructor
public class OrderManager {
    private final OrderRepository orderRepository;
    private final CartManager cartManager;
    private final Executor orderExecutor;

    public Future<Order> order(Cart cart, Client client, ZonedDateTime start) {
        String date = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<ProductToCart> productsCopy = new ArrayList<>(cart.findAll());
        Order order = Order.builder()
                .client(client)
                .products(productsCopy)
                .date(start)
                .build();
        order.setId("BK-<" + date + ">-<" + counterCreation() + ">");
        order.setTotalPrice(cartManager.calculateTotalPrice());
        return orderExecutor.getExecutorService().submit(() -> {
            orderExecutor.processOrder(order);
            orderRepository.addOrder(order);
            return order;
        });
    }

    public static int counterCreation() {
        Random random = new Random();
        return random.nextInt(1000);
    }
}
