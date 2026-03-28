package repository;

import exceptions.CantSaveToFile;
import file.FileReader;
import file.FileWriter;
import order.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements OrderReposit {
    private List<Order> orders;
    private FileReader fileReader;
    private FileWriter fileWriter;

    public OrderRepository() {
        this.orders = new ArrayList<>();
        this.fileReader = new FileReader();
        this.fileWriter = new FileWriter();
    }

    @Override
    public void addOrder(Order order) {
        orders.add(order);
        fileWriter.saveOrderToFile(order);
    }

    @Override
    public List<Order> findAll() {
        fileReader.printFromFile();
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


