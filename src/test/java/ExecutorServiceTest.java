import client.Client;
import manager.CartManager;
import manager.OrderManager;
import manager.ProductManager;
import money.Money;
import order.Order;
import order.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.Cart;
import repository.OrderRepository;
import repository.ProductRepository;
import threadsExecutor.Executor;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ExecutorServiceTest {

    @Test
    void shouldCheckHowExecutorWorks() throws ExecutionException, InterruptedException {
        // Arrange
        int threads = 3;
        int taskCount = 100;

        ProductRepository repository = new ProductRepository();
        ProductManager productManager = new ProductManager(repository);
        productManager.createSmartphone("001", "samsung", Money.of("4600"), 10000);
        Executor executor = new Executor(threads);
        OrderRepository orderRepository = new OrderRepository();
        OrderManager orderManager = new OrderManager(orderRepository, new CartManager(new Cart(), repository), executor);

        Client client = new Client("Piotr", "Nowak", "012310101");
        List<Future<Order>> futures = new ArrayList<>();

        // Act
        for (int i = 0; i < taskCount; i++) {
            Cart cart = new Cart();
            CartManager cartManager = new CartManager(cart, repository);
            cartManager.addProductToCart("001", 1);
            futures.add(orderManager.order(cart, client, ZonedDateTime.now()));
        }

        List<Order> results = new ArrayList<>();
        for (Future<Order> future : futures) {
            results.add(future.get());
        }
        executor.shutdown();

        // Assert
        assertEquals(taskCount, results.size());
        for (Order order : results) {
            assertEquals(OrderStatus.FINALIZED, order.getOrderStatus());
        }
    }

    @Test
    void shouldProcessOrdersConcurrently() throws Exception {
        // Arrange
        int threads = 3;
        int taskCount = 100;

        Executor executor = new Executor(threads);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch ready = new CountDownLatch(taskCount);
        List<Future<Order>> futures = new ArrayList<>();

        // Act
        for (int i = 0; i < taskCount; i++) {
            futures.add(executor.getExecutorService().submit(() -> {
                start.await();
                Order order = Order.builder().build();
                executor.processOrder(order);
                ready.countDown();
                return order;
            }));
        }

        start.countDown();
        ready.await();
        executor.shutdown();

        // Assert
        for (Future<Order> future : futures) {
            assertEquals(OrderStatus.FINALIZED, future.get().getOrderStatus());
        }
    }
}
