package comandLine;

import client.Client;
import exceptions.NoProductException;
import manager.CartManager;
import manager.OrderManager;
import order.Order;
import product.Computer;
import product.Electronics;
import product.Product;
import product.Smartfon;
import repository.Cart;
import repository.OrderRepository;
import repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.*;

public class Cli {
    private DataReader dataReader;
    private ProductRepository productRepository;
    private ConsolePrinter consolePrinter;
    private Cart cart;
    private CartManager cartManager;
    private OrderRepository orderRepository;
    private OrderManager orderManager;

    public Cli() {
        this.dataReader = new DataReader();
        this.productRepository = new ProductRepository();
        this.consolePrinter = new ConsolePrinter();
        this.cart = new Cart();
        this.cartManager = new CartManager(cart, productRepository);
        this.orderRepository = new OrderRepository();
        this.orderManager = new OrderManager(orderRepository);
    }

    public void controlLoop() {
        Option option = null;
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
                    case SMARTPHONE_CONFIGURATION -> smartphoneConfiguration();
                    case COMPUTER_CONFIGURATION -> computerConfiguration();
                    case INITIALIZE_CART -> addProductToCart();
                    case PRINT_CARTS -> cartPrinter();
                    case ADD_ORDER -> addOrder();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Wrong option!");
            }
        } while (option != Option.EXIT);
    }

    public void addSmartphone() {
        Smartfon smartfon = dataReader.readAndCreateSmartphone();
        productRepository.add(smartfon);
        System.out.println("Smartphone created!");
    }

    public void addComputer() {
        Computer computer = dataReader.readAndCreateComputer();
        productRepository.add(computer);
        System.out.println("Computer created!");
    }

    public void addElectronic() {
        Electronics electronics = dataReader.readAndCreateElectronic();
        productRepository.add(electronics);
        System.out.println("Electronic device created!");
    }

    public void smartphoneConfiguration() { // do dopytania, bo srednio to wyglada
        System.out.println("Insert product's id to configurate.");
        String id = dataReader.getString();
        Product product = productRepository.findProductById(id).get();
        Smartfon smartfon = (Smartfon) product; // tu nie do konca podoba mi sie to rozwiazanie
        System.out.println("Insert smartphone's color: ");
        String color = dataReader.getString();
        System.out.println("Insert battery capacity: ");
        int batteryCapacity = dataReader.getAndReturnInt();
        System.out.println("Insert additional accessory: ");
        String accessoriesInput = dataReader.getString();
        Set<String> additionalAccessory = Set.of(accessoriesInput);
        smartfon.configuration(color, batteryCapacity, additionalAccessory);
    }

    public void computerConfiguration() {
        try {
            System.out.println("Insert product's id to configurate.");
            String id = dataReader.getString();
            Product product = getProductById(id);
            Computer computer = (Computer) product;
            System.out.println("Insert computer's processor: ");
            String processor = dataReader.getString();
            System.out.println("Insert RAM memory capacity: ");
            int ramMemory = dataReader.getAndReturnInt();
            computer.configuration(processor, ramMemory);
        } catch (NoProductException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addProductToCart() {
        try {
            System.out.println("Insert product id: ");
            String productId = dataReader.getString();
            Product product = getProductById(productId);
            System.out.println("Quantity: ");
            int quantityOfProduct = dataReader.getAndReturnInt();
            cartManager.addProductToCart(product.getId(), quantityOfProduct);
            System.out.println(cart.findAll().toString());
        } catch (NoProductException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cartPrinter() {
        cartManager.printCarts();
    }

    public void productsPrinter() {
        consolePrinter.printProducts(productRepository.findAll());
    }

    public void addOrder() {
        Client client = getClientByProvidingData();
        Order order = orderManager.order(cart, client, LocalDateTime.now());
        System.out.println("Order created: " + order);
        cart.clearing();
    }

    private Client getClientByProvidingData() {
        System.out.println("Insert client's name: ");
        String clientName = dataReader.getString();
        System.out.println("Insert client's lastname: ");
        String lastName = dataReader.getString();
        System.out.println("Insert client's id: ");
        String id = dataReader.getString();
        Client client = new Client(clientName, lastName, id);
        return client;
    }

    private Product getProductById(String id) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new NoProductException("Product with id " + id + " not found: " + id));
        return product;
    }
}
