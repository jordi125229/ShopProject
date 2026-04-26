package test;

import client.Client;
import lombok.extern.slf4j.Slf4j;
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
import threadsExecutor.Executor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
@Slf4j
public class GeneralTest {
    public static void main(String[] args) {
        ProductRepository repository = new ProductRepository();
        ProductManager productManager = new ProductManager(repository);
        Cart cart = new Cart();
        CartManager cartManager = new CartManager(cart, repository);
        Executor orderExecutor = new Executor(3);
        InvoiceManager invoiceManager = new InvoiceManager(orderExecutor);
        OrderRepository orderRepository = new OrderRepository();
        OrderManager orderManager = new OrderManager(orderRepository, cartManager, orderExecutor);
        Computer lenovo = createAndConfigureComputer(productManager);
        createSmartphone(productManager, lenovo);
        Map<String, Product> all = getAndPrintAllProductFromMagazine(repository);
        addProductFromMagazineToCart(cartManager, all, cart);
        createOrderAndInvoice(orderManager, cart, invoiceManager);
        List<Order> allOrders = orderRepository.findAll();
        log.info(allOrders.toString());

    }

    private static void createOrderAndInvoice(OrderManager orderManager, Cart cart, InvoiceManager invoiceManager) {
        Order order = null;
        try {
            order = orderManager.order(cart, new Client("Piotr", "Nowak", "012310101"), ZonedDateTime.now()).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        log.info(String.valueOf(order));
//        log.info("Cart after ordering (empty): " + cart); -> to be checked, I want to clear the cart after ordering creation
        Invoice invoice = null;
        try {
            invoice = invoiceManager.toInvoice(order).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        log.info(String.valueOf(invoice));
    }

    private static void addProductFromMagazineToCart(CartManager cartManager, Map<String, Product> all, Cart cart) {
        cartManager.addProductToCart("430", 2);
        log.info("Magazine after cart creation (look at quantity): " + all);
        log.info("Cart: " + cart);
    }

    private static Map<String, Product> getAndPrintAllProductFromMagazine(ProductRepository repository) {
        Map<String, Product> all = repository.findAll();
        log.info("Magazine: " + all + '\n');
        return all;
    }

    private static void createSmartphone(ProductManager productManager, Computer lenovo) {
        productManager.createSmartphone("111231", "Iphone", Money.of("4900"), 3);
        log.info(String.valueOf(lenovo));
    }

    private static Computer createAndConfigureComputer(ProductManager productManager) {
        Computer lenovo = productManager.createComputer("430", "Lenovo", Money.of("4200"), 5);
        lenovo.configuration("AMD", 16);
        return lenovo;
    }
}
