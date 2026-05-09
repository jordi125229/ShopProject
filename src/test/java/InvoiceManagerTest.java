import client.Client;
import manager.InvoiceManager;
import manager.OrderManager;
import money.Money;
import order.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import payment.Invoice;
import product.Computer;
import product.ProductToCart;
import threadsExecutor.Executor;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceManagerTest {
    @Mock
    Executor executor;

    @InjectMocks
    private InvoiceManager invoiceManager;

    private Computer computer;

    private Order order;

    private String date;

    private int randomCounterForTests;

    private void setComputer() {
        computer = Computer.builder()
                .id("001")
                .name("Test computer")
                .price(Money.of("5000"))
                .quantity(10).build();
    }

    private void setOrder() {
        randomCounterForTests = OrderManager.counterCreation();
        date = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        order = Order.builder()
                .id("BK-<" + date + ">-<" + randomCounterForTests + ">")
                .date(ZonedDateTime.now())
                .products(List.of(new ProductToCart(computer, 1)))
                .build();
    }

    @Test
    void shouldCreateInvoice() throws ExecutionException, InterruptedException {
        // Arrange
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        when(executor.getExecutorService()).thenReturn(executorService);
        setComputer();
        setOrder();

        String expectedInvoiceNumber = "INV-<" + date + ">-<" + randomCounterForTests + ">";

        // Act
        Future<Invoice> invoice = invoiceManager.toInvoice(order);

        // Assert
        assertThat(invoice.get()).isNotNull();
        assertEquals(order.getTotalPrice(), invoice.get().getTotal());
        assertEquals(order.getClient(), invoice.get().getClient());
        assertEquals(expectedInvoiceNumber, invoice.get().getInvoiceNumber());
    }
}
