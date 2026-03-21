package manager;

import order.Order;
import payment.Invoice;
import repository.OrderRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvoiceManager {
    public Invoice toInvoice(Order order) {
        Invoice invoice = new Invoice();
        String counter = extractCounter(order.getId());
        String date = gettingDate(order);
        invoice.setInvoiceNumber("INV-<" + date + ">-<" + counter + ">");
        invoice.setIssueDate(LocalDateTime.now());
        invoice.setClient(order.getClient());
        invoice.setTotal(order.getTotalPrice());
        invoice.setItemDescription(order.getCart().toString());
        return invoice;
    }

    private String gettingDate(Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = order.getDate().format(formatter);
        return date;
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
