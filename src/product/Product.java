package product;

import money.Money;

public class Product {
    private String id;
    private String name;
    private Money price;
    private int quantity;

    public Product(String id, String name, Money price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "id '" + id + '\'' +
                ", name '" + name + '\'' + price +
                ", quantity " + quantity +
                '|';
    }
}
