package comandLine;

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
}
