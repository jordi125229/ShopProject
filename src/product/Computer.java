package product;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString(callSuper = true)
public class Computer extends Product {
    private String processor;
    private int ram;

    public void configuration(String processor, int ram) {
        this.processor = processor;
        this.ram = ram;
    }
}
