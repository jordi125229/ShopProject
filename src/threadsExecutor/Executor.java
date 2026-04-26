package threadsExecutor;

import lombok.extern.slf4j.Slf4j;
import order.Order;
import order.OrderStatus;
import payment.Invoice;

import java.util.concurrent.*;

@Slf4j
public class Executor {
    private final ExecutorService executorService;

    public Executor(int threadsNumber) {
        this.executorService = Executors.newFixedThreadPool(threadsNumber);
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void processOrder(Order order) {
        order.setOrderStatus(OrderStatus.PENDING);
        processing();
        order.setOrderStatus(OrderStatus.FINALIZED);
    }

    public void processInvoice(Invoice invoice) {
        log.info("Invoice {} is being finalized", invoice.getInvoiceNumber());
        processing();
    }

    private static void processing() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
