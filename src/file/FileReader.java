package file;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {
    private static final Path FILE_PATH = Paths.get("orders.txt");

    public void printFromFile() {
        try {
            List<String> strings = Files.readAllLines(FILE_PATH);
            System.out.println(strings);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
