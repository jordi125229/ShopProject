package commandLine;

import client.Client;
import exceptions.*;
import file.FileReader;
import file.FileWriter;
import manager.CartManager;
import manager.InvoiceManager;
import manager.OrderManager;
import manager.ProductManager;
import money.Money;
import order.Order;
import payment.Invoice;
import product.Computer;
import product.Product;
import product.Smartphone;
import repository.Cart;
import repository.InvoiceRepository;
import repository.OrderRepository;
import repository.ProductRepository;
import threadsExecutor.Executor;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class Cli {
    private final DataReader dataReader;
    private final ProductRepository productRepository;
    private final ConsolePrinter consolePrinter;
    private final Cart cart;
    private final CartManager cartManager;
    private final OrderRepository orderRepository;
    private final OrderManager orderManager;
    private final InvoiceManager invoiceManager;
    private final InvoiceRepository invoiceRepository;
    private final ProductManager productManager;
    private final FileReader fileReader;
    private final FileWriter fileWriter;
    private final Executor orderExecutor;

    public Cli() {
        this.dataReader = new DataReader();
        this.productRepository = new ProductRepository();
        this.consolePrinter = new ConsolePrinter();
        this.cart = new Cart();
        this.cartManager = new CartManager(cart, productRepository);
        this.orderRepository = new OrderRepository();
        this.orderExecutor = new Executor(3);
        this.orderManager = new OrderManager(orderRepository, cartManager, orderExecutor);
        this.invoiceManager = new InvoiceManager(orderExecutor);
        this.invoiceRepository = new InvoiceRepository();
        this.productManager = new ProductManager(productRepository);
        this.fileReader = new FileReader();
        this.fileWriter = new FileWriter();
    }

    public void controlLoop() {
        Option option = null;
        fileReader.importProductsFromFile(productManager);
        do {
            Option.printOptions();
            try {
                int insert = dataReader.getAndReturnInt();
                option = Option.fromNumber(insert);
                switch (option) {
                    case CREATE_SMARTPHONE -> addSmartphone();
                    case CREATE_COMPUTER -> addComputer();
                    case CREATE_ELECTRONIC -> addElectronic();
                    case LIST_PRODUCTS -> productsPrinter();
                    case DELETE_ITEM -> deleteItem();
                    case SMARTPHONE_CONFIGURATION -> smartphoneConfiguration();
                    case COMPUTER_CONFIGURATION -> computerConfiguration();
                    case ADD_TO_CART -> addProductToCart();
                    case PRINT_CARTS -> cartPrinter();
                    case ADD_ORDER -> addOrder();
                    case CREATE_INVOICE -> createInvoice();
                    case PRINT_INVOICES -> invoicePrinter();
                    case PRINT_ORDERS -> ordersPrinter();
                }
            } catch (IllegalArgumentException e) {
                consolePrinter.printLine(e.getMessage());
            } catch (InputMismatchException e) {
                consolePrinter.printLine("Wrong option!");
            }
        } while (option != Option.EXIT);
        fileWriter.exportProductsToFile(productRepository);
        consolePrinter.printLine("Goodbye!");
        orderExecutor.shutdown();
    }

    private void addSmartphone() {
        try {
            String id = getId();
            String name = getProductName();
            String price = getProductPrice();
            int quantity = getProductQuantity();
            productManager.createSmartphone(id, name, Money.of(price), quantity);
            consolePrinter.printLine("Smartphone created!");
        } catch (NegativeQuantityException | MoneyCantBeNegative e) {
            consolePrinter.printLine(e.getMessage() + " Please try again!");
        }
    }

    private void addComputer() {
        try {
            String id = getId();
            String name = getProductName();
            String price = getProductPrice();
            int quantity = getProductQuantity();
            productManager.createComputer(id, name, Money.of(price), quantity);
            consolePrinter.printLine("Computer created!");
        } catch (NegativeQuantityException | MoneyCantBeNegative e) {
            consolePrinter.printLine(e.getMessage() + " Please try again!");
        }
    }

    private void deleteItem() {
        consolePrinter.printLine("Insert product's id to delete:");
        String productId = dataReader.getString();
        Product productById = getProductById(productId);
        productRepository.delete(productById);
        consolePrinter.printLine("Product deleted!");
    }

    private int getProductQuantity() {
        consolePrinter.printLine("Insert quantity: ");
        return dataReader.getAndReturnInt();
    }

    private String getProductPrice() {
        consolePrinter.printLine("Insert price: ");
        return dataReader.getString();
    }

    private String getProductName() {
        consolePrinter.printLine("Insert device's name: ");
        return dataReader.getString();
    }

    private String getId() {
        consolePrinter.printLine("Insert device's id: ");
        return dataReader.getString();
    }

    private void addElectronic() {
        try {
            String id = getId();
            String name = getProductName();
            String price = getProductPrice();
            int quantity = getProductQuantity();
            productManager.createElectronic(id, name, Money.of(price), quantity);
            consolePrinter.printLine("Electronic device created!");
        } catch (NegativeQuantityException | MoneyCantBeNegative e) {
            consolePrinter.printLine(e.getMessage() + " Please try again!");
        }
    }

    private void smartphoneConfiguration() {
        try {
            consolePrinter.printLine("Insert product's id to configurate.");
            String id = dataReader.getString();
            Product product = getProductById(id);
            if (!(product instanceof Smartphone smartphone)) {
                consolePrinter.printLine("Product is not a smartphone");
                return;
            }
            consolePrinter.printLine("Insert smartphone's color: ");
            String color = dataReader.getString();
            consolePrinter.printLine("Insert battery capacity: ");
            int batteryCapacity = dataReader.getAndReturnInt();
            consolePrinter.printLine("Insert additional accessory: ");
            String accessoriesInput = dataReader.getString();
            Set<String> additionalAccessory = Set.of(accessoriesInput);
            smartphone.configuration(color, batteryCapacity, additionalAccessory);
        } catch (NoProductException e) {
            consolePrinter.printLine(e.getMessage());
        }
    }

    private void computerConfiguration() {
        try {
            consolePrinter.printLine("Insert product's id to configurate.");
            String id = dataReader.getString();
            Product product = getProductById(id);
            if (!(product instanceof Computer computer)) {
                consolePrinter.printLine("Product is not a computer");
                return;
            }
            consolePrinter.printLine("Insert computer's processor: ");
            String processor = dataReader.getString();
            consolePrinter.printLine("Insert RAM memory capacity: ");
            int ramMemory = dataReader.getAndReturnInt();
            computer.configuration(processor, ramMemory);
        } catch (NoProductException e) {
            consolePrinter.printLine(e.getMessage());
        }
    }

    private void addProductToCart() {
        try {
            consolePrinter.printLine("Insert product id: ");
            String productId = dataReader.getString();
            Product product = getProductById(productId);
            consolePrinter.printLine("Quantity: ");
            int quantityOfProduct = dataReader.getAndReturnInt();
            cartManager.addProductToCart(product.getId(), quantityOfProduct);
            consolePrinter.printLine(cart.findAll().toString());
        } catch (NoProductException e) {
            consolePrinter.printLine(e.getMessage());
        }
    }

    private void cartPrinter() {
        consolePrinter.printCarts(cart.findAll());
    }

    private void productsPrinter() {
        consolePrinter.printProducts(productRepository.findAll());
    }

    private void ordersPrinter() {
        fileReader.printOrdersFromFile();
    }

    private void addOrder() {
        try {
            if (cart.isEmpty()) {
                consolePrinter.printLine("Cart is empty. Can't create order.");
                return;
            }
            cart.findAll();
            Client client = getClientByProvidingData();
            Order order = orderManager.order(cart, client, ZonedDateTime.now()).get();
            consolePrinter.printLine("Order created: " + order);
            fileWriter.saveOrderToFile(order);
            cart.clear();
        } catch (NoProductException | InterruptedException | ExecutionException e) {
            consolePrinter.printLine(e.getMessage());
        }
    }

    private Client getClientByProvidingData() {
        consolePrinter.printLine("Insert client's name: ");
        String clientName = dataReader.getString();
        consolePrinter.printLine("Insert client's lastname: ");
        String lastName = dataReader.getString();
        consolePrinter.printLine("Insert client's id: ");
        String id = dataReader.getString();
        return new Client(clientName, lastName, id);
    }

    private void createInvoice() {
        try {
            consolePrinter.printLine("Insert order's id to create invoice: ");
            String orderId = dataReader.getString();
            Order orderById = orderRepository.findOrderById(orderId);
            Invoice invoice = invoiceManager.toInvoice(orderById).get();
            invoiceRepository.addInvoice(invoice);
            consolePrinter.printLine("Invoice created: " + invoice);
            fileWriter.saveInvoiceToFile(invoice);
        } catch (NoOrderException e) {
            consolePrinter.printLine(e.getMessage());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void invoicePrinter() {
        fileReader.printAllInvoices();
    }

    private Product getProductById(String id) {
        return productRepository.findProductById(id).orElseThrow(() -> new NoProductException("Product with id " + id + " not found"));
    }
}
