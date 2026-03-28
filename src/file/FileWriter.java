package file;

import exceptions.CantSaveToFile;
import order.Order;
import product.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {
    private static final Path FILE_PATH = Paths.get("orders.txt");
    private static final Path MAGAZINE_FILE_PATH = Paths.get("magazine.txt");

    public void saveOrderToFile(Order order) {
        try {
            Files.writeString(
                    FILE_PATH,
                    orderSerialize(order) + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new CantSaveToFile("Error saving order to file");
        }
    }

    public void saveProductToFile(Product product) {
        try {
            Files.writeString(
                    MAGAZINE_FILE_PATH,
                    productSerialize(product) + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new CantSaveToFile("Error saving order to file");
        }
    }

    private String productSerialize(Product product) {
        return String.join(";",
                product.getId(),
                product.getName(),
                product.getPrice().toString()
        );
    }

    private String orderSerialize(Order order) {
        return String.join(";",
                order.getId(),
                order.getClient().getName(),
                order.getCart().serialize(),
                order.getTotalPrice().toString(),
                order.getDate().toString()
        );
    }
}
