package file;


import order.Order;
import payment.Invoice;
import product.ProductToCart;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {
    private static final Path FILE_PATH_ORDERS = Paths.get("orders.txt");
    private static final Path FILE_PATH_INVOICES = Paths.get("invoices.txt");

    public void saveOrderToFile(Order order) {
        String orderToString = formatOrder(order);
        try {
            Files.writeString(
                    FILE_PATH_ORDERS,
                    orderToString,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException("Can't save order");
        }
    }

    private String formatOrder(Order order) { // z tym formatowaniem wspomagalem sie LLMem
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("=== ORDER ===\n");
        stringBuilder.append("ID: ").append(order.getId()).append("\n");
        stringBuilder.append("Client: ").append(order.getClient().getName()).append("\n");
        stringBuilder.append("Products:\n");
        for (ProductToCart p : order.getProducts()) {
            stringBuilder.append("- ")
                    .append(p.getProduct().getName())
                    .append(" | ")
                    .append(p.getProduct().getPrice())
                    .append(" | qty: ")
                    .append(p.getQuantity())
                    .append("\n");
        }
        stringBuilder.append("Total: ").append(order.getTotalPrice()).append("\n");
        stringBuilder.append("Date: ").append(order.getDate()).append("\n");
        return stringBuilder.toString();
    }

    public void saveInvoiceToFile(Invoice invoice) {
        String invoiceToString = formatInvoice(invoice);
        try {
            Files.writeString(
                    FILE_PATH_INVOICES,
                    invoiceToString,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException("Can't save invoice");
        }
    }

    private String formatInvoice(Invoice invoice) {
        return "=== INVOICE ===\n" +
                "Number: " + invoice.getInvoiceNumber() + "\n" +
                "Client: " + invoice.getClient().getName() + "\n" +
                "Total: " + invoice.getTotal() + "\n" +
                "Date: " + invoice.getIssueDate() + "\n" +
                "Description: " + invoice.getItemDescription();
    }
}
