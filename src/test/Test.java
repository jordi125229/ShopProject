package test;

import client.Client;
import manager.CartManager;
import manager.OrderManager;
import manager.ProductManager;
import money.Money;
import order.Order;
import product.Computer;
import product.Product;
import product.Smartfon;
import repository.Cart;
import repository.OrderRepository;
import repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        ProductRepository repository = new ProductRepository();
        ProductManager productManager = new ProductManager(repository);
        Cart cart = new Cart();
        CartManager cartManager = new CartManager(cart, repository);
        OrderRepository orderRepository = new OrderRepository();
        OrderManager orderManager = new OrderManager(orderRepository);
        createComputerTest(productManager);
        createSmartfonTest(productManager);
        computerConfigurationTest(productManager);
        testOfAddingProductsToMap(repository);
//        testOfRemovingFromMap(productManager, repository);
        testOfProductUpdate(productManager, repository);
        testOfCartWorking(repository, cartManager, cart);
        cartClearingTest(cart);
        orderCreateTest(cartManager, orderManager, cart, repository);
        printAllOrdersTest(orderRepository);
    }

    private static void createSmartfonTest(ProductManager productManager) {
        System.out.println("Test2: creating smartfon");
        Smartfon smartfon1 = productManager.createSmartfon("005", "samsung", Money.of("4600"), 10);
        System.out.println(smartfon1 + "\n");
    }

    private static void createComputerTest(ProductManager productManager) {
        System.out.println("Test1: creating computer");
        Computer computer1 = productManager.createComputer("014", "HP", Money.of("2800"), 5);
        System.out.println(computer1 + "\n");
    }

    private static void computerConfigurationTest(ProductManager productManager) {
        System.out.println("Test3: configuration of computer");
        Computer computer2 = productManager.createComputer("014", "HP", Money.of("2800"), 5);
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

    private static void testOfProductUpdate(ProductManager productManager, ProductRepository repository) {
        System.out.println("Test 6: Product update");
        productManager.updateProduct("005", "sony", Money.of("3200"), 10);
        Optional<Product> productById = repository.findProductById("005");
        System.out.println(productById.get() + "\n");
    }

    private static void testOfCartWorking(ProductRepository repository, CartManager cartManager, Cart cart) {
        System.out.println("Test 7: cart creation");
        String string = repository.findAll().toString();
        System.out.println(string);
        cartManager.addProductToCart("005", 1);
        cart.findAll().forEach(System.out::println);
        System.out.println();
    }

    private static void cartClearingTest(Cart cart) {
        System.out.println("Test 8: cart's clearing");
        cart.clearing();
        cart.findAll().forEach(System.out::println);
        System.out.println();
    }

    private static void orderCreateTest(CartManager cartManager, OrderManager orderManager, Cart cart, ProductRepository repository) {
        System.out.println("Test 9: Ordering");
        cartManager.addProductToCart("005", 2);
        cartManager.addProductToCart("014", 5);
        Order order = orderManager.order(cart, new Client("Piotr", "Nowak", "010311041"), LocalDateTime.now());
        System.out.println(order);
        String string = repository.findAll().toString();
        System.out.println("Warehouse after ordering: " + "\n" + string + "\n");
    }

    private static void printAllOrdersTest(OrderRepository orderRepository) {
        System.out.println("Test 10: Orders printing");
        List<Order> allOrders = orderRepository.findAll();
        System.out.println(allOrders);
    }
}
