package comandLine;

import order.Order;
import payment.Invoice;
import product.Product;

import java.util.List;
import java.util.Map;

public class ConsolePrinter {
    public void printLine(String text) {
        System.out.println(text);
    }

    public void printProducts(Map<String, Product> productsMap) {
        printLine(productsMap.toString());
    }

    public void printInvoices(List<Invoice> invoices) {
        printLine(invoices.toString());
    }

//    public void printOrders(List<Order> orders) { narazie niepotrzebna metoda
//        printLine(orders.toString());
//    }

    public void printCarts(List<Product> productsBucket) {
        printLine(productsBucket.toString());
    }
}
