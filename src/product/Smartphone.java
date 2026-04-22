package product;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import java.util.Set;

@SuperBuilder
@ToString(callSuper = true)
public class Smartphone extends Product {
    private String color;
    private int batteryCapacity;
    private Set<String> additionalAccessory;

    public void configuration(String color, int batteryCapacity, Set<String> additionalAccessory) {
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.additionalAccessory = additionalAccessory;
    }
}