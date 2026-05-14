package order;

import client.Client;
import money.Money;
import product.Product;
import product.productToCart.ProductToCart;
import repository.Cart;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Client client;
    private List<ProductToCart> products;
    private Money totalPrice;
    private ZonedDateTime date;
    private String id;

    public ZonedDateTime getDate() {
        return date;
    }

    public Order(Client client, List<ProductToCart> products, ZonedDateTime date) {
        this.client = client;
        this.products = products;
        this.date = date;
    }

    public List<ProductToCart> getProducts() {
        return products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Money totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "client=" + client +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                ", id='" + id + '\'' +
                '}';
    }

    public String serialize() {
        return products.stream()
                .map(p -> p.getProduct().getName() + "," + p.getProduct().getPrice() + "," + p.getQuantity())
                .reduce((a, b) -> a + b)
                .orElse("");
    }
}