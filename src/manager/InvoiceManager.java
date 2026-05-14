package manager;

import order.Order;
import payment.Invoice;
import threadsExecutor.Executor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvoiceManager {
    private Executor orderExecutor;

    public InvoiceManager(Executor orderExecutor) {
        this.orderExecutor = orderExecutor;
    }

    public Invoice toInvoice(Order order) {
        Invoice invoice = new Invoice();
        String counter = extractCounter(order.getId());
        String date = gettingDate(order);
        invoice.setInvoiceNumber("INV-<" + date + ">-<" + counter + ">");
        invoice.setIssueDate(LocalDateTime.now());
        invoice.setClient(order.getClient());
        invoice.setTotal(order.getTotalPrice());
        invoice.setItemDescription(order.getProducts().toString());
        Future<?> futureInvoice = orderExecutor.invoiceProcessing(invoice);
        try {
            futureInvoice.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return invoice;
    }

    private String gettingDate(Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return order.getDate().format(formatter);
    }

    private String extractCounter(String bookingId) {
        Pattern pattern = Pattern.compile("BK-<.+-(\\d+)>");
        Matcher matcher = pattern.matcher(bookingId);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }
}
