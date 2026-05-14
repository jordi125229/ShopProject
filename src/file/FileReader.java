package file;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {
    private static final Path FILE_PATH_ORDERS = Paths.get("orders.txt");
    private static final Path FILE_PATH_INVOICES = Paths.get("invoices.txt");

    public void printOrdersFromFile() {
        try {
            List<String> lines = Files.readAllLines(FILE_PATH_ORDERS);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Can't read orders");
        }
    }

    public void printAllInvoices() {
        try {
            List<String> lines = Files.readAllLines(FILE_PATH_INVOICES);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Can't' read invoices");
        }
    }
}
