package repository;

import money.Money;
import order.Order;
import product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository implements OrderReposit {
    private List<Order> orders;

    public OrderRepository() {
        this.orders = new ArrayList<>();
    }

    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }

    @Override
    public Order findOrderById(String id) {
        return orders.stream()
                .filter(order -> order.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can't find order number " + id));
    }
}
