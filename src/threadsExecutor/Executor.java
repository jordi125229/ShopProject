package threadsExecutor;

import order.Order;
import order.OrderStatus;
import payment.Invoice;

import java.util.concurrent.*;

public class Executor {
    ExecutorService executorService;

    public Executor(int threadsNumber) {
        this.executorService = Executors.newFixedThreadPool(threadsNumber);
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public Future<?> orderProcessing(Order order) {
        return executorService.submit(() -> {
            order.setOrderStatus(OrderStatus.PENDING);
            processing();
            System.out.println("Order " + order.getId() + " is being finalized");
            order.setOrderStatus(OrderStatus.FINALIZED);
        });
    }

    public Future<?> invoiceProcessing(Invoice invoice) {
        return executorService.submit(() -> {
            System.out.println("Invoice " + invoice.getInvoiceNumber() + " is being finalized");
            processing();
        });
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
