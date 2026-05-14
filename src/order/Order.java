package order;

import client.Client;
import money.Money;
import product.Product;
import product.productToCart.ProductToCart;
import repository.Cart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Client client;
    private List<ProductToCart> products;
    private Money totalPrice;
    private LocalDateTime date;
    private String id;

    public LocalDateTime getDate() {
        return date;
    }

    public Order(Client client, List<ProductToCart> products, LocalDateTime date) {
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
}