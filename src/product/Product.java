package product;

import money.Money;

public abstract class Product {
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

    public void setQuantity(int quantity) {
        if (quantity < 0){
            throw new IllegalArgumentException("Quantity can't be less than 0!");
        }
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
