package test;

import manager.ProductManager;
import money.Money;
import product.Computer;
import product.Smartfon;
import repository.ProductRepository;

public class Test {
    public static void main(String[] args) {
        ProductRepository repository = new ProductRepository();
        ProductManager productManager = new ProductManager(repository);

        createComputerTest(productManager);
        createSmartfonTest(productManager);
        computerConfigurationTest(productManager);
        testOfAddingProductsToMap(repository);
        testOfRemovingFromMap(productManager, repository);
    }

    private static void createSmartfonTest(ProductManager productManager) {
        System.out.println("Test2: creating smartfon");
        Smartfon smartfon1 = productManager.smartfonCreator("005", "samsung", Money.of("4600"), 10);
        System.out.println(smartfon1 + "\n");
    }

    private static void createComputerTest(ProductManager productManager) {
        System.out.println("Test1: creating computer");
        Computer computer1 = productManager.computerCreator("014", "HP", Money.of("2800"), 4);
        System.out.println(computer1 + "\n");
    }

    private static void computerConfigurationTest(ProductManager productManager) {
        System.out.println("Test3: configuration of computer");
        Computer computer2 = productManager.computerCreator("014", "HP", Money.of("2800"), 4);
        System.out.println(computer2);
        computer2.configuration("AMD", 16);
        System.out.println(computer2 + "\n");
    }

    private static void testOfAddingProductsToMap(ProductRepository repository) {
        System.out.println("Test 4: how map works");
        String string = repository.findAll().toString();
        System.out.println(string + "\n");
    }

    private static void testOfRemovingFromMap(ProductManager productManager, ProductRepository repository) {
        System.out.println("Test 5: removing from map");
        productManager.productDeleting("014");
        String string = repository.findAll().toString();
        System.out.println(string + "\n");
    }
}
