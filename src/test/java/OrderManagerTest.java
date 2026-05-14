
import client.Client;
import manager.CartManager;
import manager.OrderManager;
import money.Money;
import order.Order;
import order.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import product.Computer;
import product.ProductToCart;
import repository.Cart;
import threadsExecutor.Executor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderManagerTest {

    @Mock
    Executor executor;

    @Mock
    Cart cart;

    @Mock
    CartManager cartManager;

    @InjectMocks
    OrderManager orderManager;

    private Computer computer;

    private Client client;

    private void setComputer() {
        computer = Computer.builder()
                .id("001")
                .name("Test computer")
                .price(Money.of("5000"))
                .quantity(10).build();
    }

    private void setClient() {
        client = new Client("name", "lastName", "id");
    }

    @Test
    void shouldCreateOrder() {
        // Arrange
        setComputer();
        setClient();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        when(cart.findAll()).thenReturn(List.of(new ProductToCart(computer, 1)));
        when(executor.getExecutorService()).thenReturn(executorService);

        // Act
        Future<Order> testOrder = orderManager.order(cart, client, ZonedDateTime.now());

        // Assert
        assertNotNull(testOrder);
    }

    @Test
    void shouldProcessOrderAndChangeStatus() {
        // Arrange
        threadsExecutor.Executor executor = new threadsExecutor.Executor(2);
        Order order = Order.builder().build();
        order.setOrderStatus(OrderStatus.PENDING);

        // Act
        executor.processOrder(order);

        // Assert
        assertEquals(OrderStatus.FINALIZED, order.getOrderStatus());
    }
}
