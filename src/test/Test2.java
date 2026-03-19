package test;

import client.Client;
import manager.CartManager;
import manager.OrderManager;
import manager.ProductManager;
import money.Money;
import order.Order;
import product.Computer;
import product.Product;
import repository.Cart;
import repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public class Test2 {
    public static void main(String[] args) {
        ProductRepository repository = new ProductRepository();
        ProductManager productManager = new ProductManager(repository);
        Cart cart = new Cart();
        CartManager cartManager = new CartManager(cart, repository);
        OrderManager orderManager = new OrderManager();
        Computer lenovo = createAndConfigureComputer(productManager);
        createSmartfon(productManager, lenovo);
        Map<String, Product> all = getAndPrintAllProductFromMagazine(repository);
        addProductFromMagazineToCart(cartManager, all, cart);
        createOrder(orderManager, cart);
    }

    private static void createOrder(OrderManager orderManager, Cart cart) {
        Order order = orderManager.order(cart, new Client("Piotr", "Nowak", "012310101"), LocalDateTime.now());
        System.out.println(order);
        System.out.println("Cart after ordering (empty): " + cart);
    }

    private static void addProductFromMagazineToCart(CartManager cartManager, Map<String, Product> all, Cart cart) {
        cartManager.addProductToCart("430", 2);
        System.out.println("Magazine after cart creation (look at quantity): " + all);
        System.out.println("Cart: " + cart);
    }

    private static Map<String, Product> getAndPrintAllProductFromMagazine(ProductRepository repository) {
        Map<String, Product> all = repository.findAll();
        System.out.println("Magazine: " + all + '\n');
        return all;
    }

    private static void createSmartfon(ProductManager productManager, Computer lenovo) {
        productManager.createSmartfon("111231", "Iphone", Money.of("4900"), 3);
        System.out.println(lenovo);
    }

    private static Computer createAndConfigureComputer(ProductManager productManager) {
        Computer lenovo = productManager.createComputer("430", "Lenovo", Money.of("4200"), 5);
        lenovo.configuration("AMD", 16);
        return lenovo;
    }
}
