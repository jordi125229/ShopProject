package test;

import client.Client;
import manager.CartManager;
import manager.InvoiceManager;
import manager.OrderManager;
import manager.ProductManager;
import money.Money;
import order.Order;
import payment.Invoice;
import product.Computer;
import product.Product;
import repository.Cart;
import repository.OrderRepository;
import repository.ProductRepository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Test2 {
    public static void main(String[] args) {
        ProductRepository repository = new ProductRepository();
        ProductManager productManager = new ProductManager(repository);
        Cart cart = new Cart();
        CartManager cartManager = new CartManager(cart, repository);
        InvoiceManager invoiceManager = new InvoiceManager();
        OrderRepository orderRepository = new OrderRepository();
        OrderManager orderManager = new OrderManager(orderRepository);
        Computer lenovo = createAndConfigureComputer(productManager);
        createSmartfon(productManager, lenovo);
        Map<String, Product> all = getAndPrintAllProductFromMagazine(repository);
        addProductFromMagazineToCart(cartManager, all, cart);
        createOrderAndInvoice(orderManager, cart, invoiceManager);

        List<Order> allOrders = orderRepository.findAll();
        System.out.println(allOrders);

    }

    private static void createOrderAndInvoice(OrderManager orderManager, Cart cart, InvoiceManager invoiceManager) {
        Order order = orderManager.order(cart, new Client("Piotr", "Nowak", "012310101"), ZonedDateTime.now());
        System.out.println(order);
//        System.out.println("Cart after ordering (empty): " + cart); -> to be checked, I want to clear the cart after ordering creation
        Invoice invoice = invoiceManager.toInvoice(order);
        System.out.println(invoice);
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
