package product;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProductToCart {
    private final Product product;
    private final int quantity;

    public ProductToCart(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity can't be lower than 0");
        }
        this.product = product;
        this.quantity = quantity;
    }
}
