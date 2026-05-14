package commandLine;

import lombok.extern.slf4j.Slf4j;
import product.Product;
import product.ProductToCart;
import java.util.Collection;
import java.util.Map;

@Slf4j
public class ConsolePrinter {
    public void printLine(String text) {
        log.info(text);
    }

    public void printProducts(Map<String, Product> productsMap) {
        for (Product p : productsMap.values()) {
            printLine(p.toString());
        }
    }

    public void printCarts(Collection<ProductToCart> productsBucket) {
        printLine(productsBucket.toString());
    }
}
