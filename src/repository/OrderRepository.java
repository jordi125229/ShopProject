package repository;

import exceptions.NoOrderException;
import order.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
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
                .orElseThrow(() -> new NoOrderException("Can't find order number " + id));
    }
}
