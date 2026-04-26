package file;


import lombok.extern.slf4j.Slf4j;
import manager.ProductManager;
import money.Money;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Slf4j
public class FileReader {
    private static final Path FILE_PATH_ORDERS = Paths.get("src/dataBase/orders.txt");
    private static final Path FILE_PATH_INVOICES = Paths.get("src/dataBase/invoices.txt");
    private static final Path FILE_PATH_PRODUCTS = Paths.get("src/dataBase/products.txt");

    public void printOrdersFromFile() {
        try {
            List<String> lines = Files.readAllLines(FILE_PATH_ORDERS);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            log.info("Can't read orders");
        }
    }

    public void importProductsFromFile(ProductManager productManager) {
        try {
            List<String> lines = Files.readAllLines(FILE_PATH_PRODUCTS);
            lines.stream().map(line -> line.split(","))
                    .forEach(variable -> {
                        String electronicType = variable[0];
                        switch (electronicType) {
                            case "Computer" -> productManager.createComputer(variable[1], variable[2],
                                    new Money(new BigDecimal(variable[4])), Integer.parseInt(variable[3]));
                            case "Smartphone" -> productManager.createSmartphone(variable[1], variable[2],
                                    new Money(new BigDecimal(variable[4])), Integer.parseInt(variable[3]));
                            default -> productManager.createElectronic(variable[1], variable[2],
                                    new Money(new BigDecimal(variable[4])), Integer.parseInt(variable[3]));
                        }
                    });
        } catch (IOException e) {
            log.info("Can't import from file!");
        }
    }

    public void printAllInvoices() {
        try {
            List<String> lines = Files.readAllLines(FILE_PATH_INVOICES);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            log.info("Can't' read invoices from file: " + FILE_PATH_INVOICES + ". File doesn't exist");
        }
    }
}
