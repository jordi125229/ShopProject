package repository;

import money.Money;
import order.Order;
import product.Product;

import java.util.List;
import java.util.Optional;

public interface OrderReposit {
    void addOrder(Order order);

    List<Order> findAll();

    Order findOrderById(String id);
}
