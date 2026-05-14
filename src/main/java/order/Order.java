package order;

import client.Client;
import lombok.Builder;
import lombok.Data;
import money.Money;
import product.ProductToCart;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
public class Order {
    private Client client;
    private List<ProductToCart> products;
    private Money totalPrice;
    private ZonedDateTime date;
    private String id;
    private OrderStatus orderStatus;
}