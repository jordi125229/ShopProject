package file;


import order.Order;
import payment.Invoice;
import product.Computer;
import product.Product;
import product.Smartphone;
import product.productToCart.ProductToCart;
import repository.ProductRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.List;

public class FileWriter {
    private static final Path FILE_PATH_ORDERS = Paths.get("orders.txt");
    private static final Path FILE_PATH_INVOICES = Paths.get("invoices.txt");
    private static final Path FILE_PATH_PRODUCTS = Paths.get("products.txt");

    public void exportProductsToFile(ProductRepository productRepository) {
        Collection<Product> products = productRepository.findAll().values();
        List<String> list = products.stream()
                .map(product -> {
                    String electronicType;
                    if (product instanceof Computer) {
                        electronicType = "Computer,";
                    } else if (product instanceof Smartphone) {
                        electronicType = "Smartphone,";
                    } else {
                        electronicType = "Electronic,";
                    }
                    return String.join(",", electronicType + product.getId(),
                            product.getName(), String.valueOf(product.getQuantity()), product.getPrice().getAmount().toString()
                    );
                })
                .toList();
        try {
            Files.write(FILE_PATH_PRODUCTS, list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveOrderToFile(Order order) {
        String orderToString = formatOrder(order);
        try {
            Files.writeString(FILE_PATH_ORDERS, orderToString, StandardOpenOption.CREATE, StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException("Can't save order");
        }
    }

    public void saveSmartphoneToFile(Product product) {
        String productToString = formatProduct(product);
        try {
            Files.writeString(FILE_PATH_PRODUCTS, productToString, StandardOpenOption.CREATE
            );
        } catch (IOException e) {
            throw new RuntimeException("Can't save order");
        }
    }

    public void saveComputerToFile(Product product) {
        String productToString = formatProduct(product);
        try {
            Files.writeString(FILE_PATH_PRODUCTS, productToString, StandardOpenOption.CREATE
            );
        } catch (IOException e) {
            throw new RuntimeException("Can't save order");
        }
    }

    public void saveInvoiceToFile(Invoice invoice) {
        String invoiceToString = formatInvoice(invoice);
        try {
            Files.writeString(FILE_PATH_INVOICES, invoiceToString, StandardOpenOption.CREATE, StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException("Can't save invoice");
        }
    }

    private String formatProduct(Product product) {
        return product.getId() + "," + product.getName() + "," + product.getQuantity() + "," + product.getPrice().getAmount() + "\n";
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
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private String formatInvoice(Invoice invoice) {
        return "=== INVOICE ===\n" +
                "Number: " + invoice.getInvoiceNumber() + "\n" +
                "Client: " + invoice.getClient().getName() + "\n" +
                "Total: " + invoice.getTotal() + "\n" +
                "Date: " + invoice.getIssueDate() + "\n" +
                "Description: " + invoice.getItemDescription() + "\n";
    }
}
