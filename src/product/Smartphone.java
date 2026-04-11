package product;

import money.Money;

import java.util.Set;

public class Smartphone extends Product {
    private String color;
    private int batteryCapacity;
    private Set<String> additionalAccessory;

    public Smartphone(String id, String name, Money price, int quantity) {
        super(id, name, price, quantity);
    }

    public void configuration(String color, int batteryCapacity, Set<String> additionalAccessory) {
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.additionalAccessory = additionalAccessory;
    }

    @Override
    public String toString() {
        return "Smartphone{" + super.toString() +
                "color='" + color + '\'' +
                ", batteryCapacity=" + batteryCapacity +
                ", additionalAccessory='" + additionalAccessory + '\'' +
                '}';
    }
}