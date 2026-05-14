package repository;

import order.Order;

import java.util.List;

public interface IOrderRepository {
    void addOrder(Order order);

    List<Order> findAll();

    Order findOrderById(String id);
}
