package order;

import client.Client;
import money.Money;
import repository.Cart;

import java.time.LocalDateTime;

public class Order {
    private Client client;
    private Cart cart;
    private Money totalPrice;
    private LocalDateTime start;
    private LocalDateTime end;
    private String id;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
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
        return "Order{" +
                "client=" + client +
                ", cart=" + cart +
                ", totalPrice=" + totalPrice +
                ", start=" + start +
                ", end=" + end +
                ", id='" + id + '\'' +
                '}';
    }
}

/*Obsługa Zamówień
Task 5: Stworzenie klasy Order reprezentującej zamówienie z informacjami o kliencie, produktach i sumie zamówienia.*/