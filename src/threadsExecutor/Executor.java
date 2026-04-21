package threadsExecutor;

import order.Order;
import order.OrderStatus;
import payment.Invoice;

import java.util.concurrent.*;

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
        System.out.println("Invoice " + invoice.getInvoiceNumber() + " is being finalized");
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
