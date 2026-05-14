package product;

import money.Money;

public class Computer extends Product {
    private String processor;
    private int ram;

    public Computer(String id, String name, Money price, int quantity) {
        super(id, name, price, quantity);
    }

    public Computer configuration(String processor, int ram) {
        this.processor = processor;
        this.ram = ram;
        return this;
    }

    @Override
    public String toString() {
        return "Computer - " + super.toString() +
                "processor:'" + processor + '\'' +
                ", ram: " + ram + "|";
    }
}
