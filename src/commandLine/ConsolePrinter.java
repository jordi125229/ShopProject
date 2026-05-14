package commandLine;

import order.Order;
import payment.Invoice;
import product.Product;
import product.ProductToCart;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ConsolePrinter {
    public void printLine(String text) {
        System.out.println(text);
    }

    public void printProducts(Map<String, Product> productsMap) {
        for (Product p : productsMap.values()) {
            printLine(p.toString());
        }
    }

    public void printInvoices(List<Invoice> invoices) {
        printLine(invoices.toString());
    }

    public void printOrders(List<Order> orders) {
        printLine(orders.toString());
    }

    public void printCarts(Collection<ProductToCart> productsBucket) {
        printLine(productsBucket.toString());
    }
}
