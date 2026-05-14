package product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import money.Money;

@Data
@SuperBuilder
@AllArgsConstructor

public abstract class Product {
    private String id;
    private String name;
    private Money price;
    private int quantity;

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity can't be less than 0!");
        }
        this.quantity = quantity;
    }
}
