package threadsExecutor;

import order.Order;
import order.OrderStatus;
import payment.Invoice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class OrderExecutor {
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    public Future<?> orderProcessing(Order order) {
        return executorService.submit(() -> {
            order.setOrderStatus(OrderStatus.PENDING);
            System.out.println("Order " + order.getId() + " is being finalized");
            processing();
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
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
