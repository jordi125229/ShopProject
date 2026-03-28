package order;

import client.Client;
import money.Money;
import repository.Cart;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Order {
    private Client client;
    private Cart cart;
    private Money totalPrice;
    private ZonedDateTime date;
    private String id;

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
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

    public void setClient(Client client) {
        this.client = client;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Money totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order: " + "client: " + client +
                ", " + cart +
                ", totalPrice " + totalPrice +
                ", order's date " + date +
                ", id " + id + '\'' +
                '}';
    }
}