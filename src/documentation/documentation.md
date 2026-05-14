# Shop project

The project is a model for an online store selling electronics.
It allows us to manage warehouse stock, carts, orders and invoices.
Application give us possibility to issue more than one order or invoice.
The application follows a layered architecture:

- **Model** – represents business entities (Product, Order, Invoice)
- **Repository** – responsible for data storage
- **Manager (Service layer)** – contains business logic
- **CLI (Presentation layer)** – handles user interaction

## Model

Project contains classes dedicated as a model. These classes represent single object which store basic information about
the elements which the project bases on.
The model represents business data and is a representation of the actual entities found in the store.

### Product

`Product` class is the superior class in the hierarchy.
It's **abstract** class. We're not supposed to create `Product` objects. The class contains fundamental fields as the
ones showed below.

```java
public abstract class Product {
    private String id;
    private String name;
    private Money price;
    private int quantity;
}
```

`Computer` extends Product to include processor and Ram. Computer is the mapping of real computer product in live going
shop.

```java
  public class Computer extends Product {
    private String processor;
    private int ram;
}
  ```

Additionally, it contains method to configure the device by addition fields as processor and ram memory:

```java
  public void configuration(String processor, int ram) {
    this.processor = processor;
    this.ram = ram;
}
```

`Smartphone` extends Product to include color and battery capacity. It also can be developed by additional accessory.
Smartphone is the mapping of real smartphone product in live going shop.

```java
public class Smartphone extends Product {
    private String color;
    private int batteryCapacity;
    private Set<String> additionalAccessory;
}
```

This class also contains method do configure the device to the filed booked only for this device.

```java
public void configuration(String color, int batteryCapacity, Set<String> additionalAccessory) {
    this.color = color;
    this.batteryCapacity = batteryCapacity;
    this.additionalAccessory = additionalAccessory;
}
```

`Electronics` extends Product but not for the purpose of adding fields

```java
public class Electronics extends Product {
}
```

### Order

`Order` is the class intended for storing the single order. This class represents placed orders in the shop. It contains
fields as a client, list of product added to order.
There was also used special class `Money` dedicated to create total price to be paid for the order. To setting the
order's data, there was used API class `ZoneDateTime`.
Orders contains **id** to be recognized and to generate invoices easily.

```java
public class Order {
    private Client client;
    private List<ProductToCart> products;
    private Money totalPrice;
    private ZonedDateTime date;
    private String id;
    private OrderStatus orderStatus;
}
  ```  

### Invoice

`Invoice` is the class intended for storing the single invoice. It represents actual invoice, so it contains payment
information.

```java
public class Invoice {
    private String invoiceNumber;
    private LocalDateTime issueDate;
    private Client client;
    private Money total;
    private String itemDescription;
}
```  

## Repositories

In the repositories we store the objects. They're like warehouse for our Objects (not only the products but also the
invoices and orders).
We can move through the repositories by package of method which allow us to add and find elements of the collections
included in the repositories.
All repositories have implementation of interfaces which allows us to develop them in the future.

### Product Repository

`ProductRepository` is a magazine for our products. We store the products in Map to find to have faster access to out
products by its 'id'. Products are saved in the Map where **id** is a key, and the **product** is a value

```java
public class ProductRepository implements IProductsRepository {
    private final Map<String, Product> products;
}
```

### Cart

`Cart` is temporary repository for products which are taken from the magazine and might be processed as order in the
future.
We store the product during the time we choose the other one. After complete the card, we can move to create order.

```java
public class Cart implements ICart {
    private Map<String, ProductToCart> productsBucket;
}
```

### Order Repository

`OrderRepository` is the storage for completed orders. In this class, users can move through the order by finding them
in the base. This is important class as a backup of the movements through the magazine. In the repository of orders, was
implemented **List** as a warehouse for the orders.

```java
public class OrderRepository implements IOrderRepository {
    private List<Order> orders;
}
```

### Invoice Repository

`InvoiceRepository` similarly as others repository is the storage for invoices. This class is really important as a
collector of the information about the sales. It stores sales documentation. There is also implemented **List** as a
collector, and we can easily find the invoices in the collection.

```java
public class InvoiceRepository implements IInvoiceRepository {
    private List<Invoice> invoices;
}
```

## Manager

`Manager` is the package of classes intended for managing the resources. Thanks to these classes, we can create the
products, initialize the cart, process the orders and issuing invoices. The all business logic is included in this
package.

### Product Manager

`ProductManager` is dedicated to working on the product. Thanks to this class, we can create the products. Product
manager has connection to the repository of product.

```java
public class ProductManager {
    private final ProductRepository productRepository;
}
```

So when we create new device, it's added to the warehouse of products.

### Cart Manager

`CartManager` is a class designed for working on product which are supposed to be bought. We add products to cart by
using this manager class. In this cart, the logic of the price calculation was included, as well the policy of discount
calculation.

```java
public Money calculateTotalPrice();

private static Money discountsCalculation(int itemQuantity, Money sum);
```

### Order Manager

`OrderManager` is a class designed for creation the orders. This class is designed to conduct process of ordering in
appropriate way.

### Invoice Manager

`InvoiceManager` is important class for providing invoices to our repository. This class is responsible for creating
invoices basing on orders.
Every invoice get its unique invoice's id.

## Command Line

`package commandLine` is the package responsible for communication with user. In this package we have classes
responsible for
data reading and data printing to the console. This package is the connection with others managing classes and it brings
together all logic.

### Cli

`Cli` class serves as the central controller for the command-line interface (CLI) in the store application, integrating
all key business components of the system, such as product management (ProductManager, ProductRepository), the shopping
cart (Cart, CartManager), orders (OrderManager, OrderRepository), and invoices (InvoiceManager, InvoiceRepository) .
Dependencies are initialized in the constructor, reflecting the layered architecture and separation of concerns. The
controlLoop() method implements the main application loop, allowing the user to select operations:

| Option's nr | Enum (Option)            | Function's description                | Method sygnature                  |
|-------------|--------------------------|---------------------------------------|-----------------------------------|
| 1           | CREATE_SMARTPHONE        | Creating a new smartphone             | `addSmartphone()`                 |
| 2           | CREATE_COMPUTER          | Creating a new computer               | `addComputer()`                   |
| 3           | CREATE_ELECTRONIC        | Creating a new electronic device      | `addElectronic()`                 |
| 4           | LIST_PRODUCTS            | Show list of products in the magazine | `productsPrinter()`               |
| 5           | SMARTPHONE_CONFIGURATION | Configuration of smartphone           | `smartphoneConfiguration()`       |
| 6           | COMPUTER_CONFIGURATION   | Configuration of computer             | `computerConfiguration()`         |
| 7           | ADD_TO_CART              | Add product to the cart               | `addProductToCart()`              |
| 8           | PRINT_CARTS              | Show the cart                         | `cartPrinter()`                   |
| 9           | ADD_ORDER                | Processing the order                  | `addOrder()`                      |
| 10          | CREATE_INVOICE           | Issuing the invoice                   | `createInvoice()`                 |
| 11          | PRINT_INVOICES           | Showing all stored invoices           | `invoicePrinter()`                |
| 12          | PRINT_ORDERS             | Showing all stored orders             | `ordersPrinter()`                 |
| 13          | EXIT                     | Closing the aplication                | no method - only closing the loop |

Handling input errors, and ensuring system continuity. Individual methods are responsible for specific use
cases—creating products, configuring them, adding them to the shopping cart, finalizing orders, and issuing
invoices. The class supports data storage through read and write operations on
files (FileReader, FileWriter) and utilizes asynchronous order processing (Executor), which enhances the system’s
performance and scalability. From a business perspective, Cli provides a simple yet comprehensive operational interface
for managing sales and administrative processes. It contains a number of method to

### Data reader

`DataReader` is the class dedicated to gathering the data from the user in the console. It contains only two method to
get the number or word. It's the only class when we use API class **Scanner**.

```java
public int getAndReturnInt();

public String getString();
```

### Console Printer

`ConsolePrinter` is the class dedicated to printing information in the console. It's the only class when we use API
method System.out.println():

```java
public void printLine(String text) {
    System.out.println(text);
}
```

We implement this class in others to print communication in the console.

## File

`package file` is dedicated to store the data. In this package we use mechanism of file reading and file writing,
included
in Java's API.
This class allows us to store products in the magazine, orders, and invoices. The class `FileWriter` is designed to
save product in the way which make us export them to the repositories easily. In this module, we can also clearly record
orders and invoices. Documentation as orders and invoices are only historical evidence. We don't use them for processing
in the future. The class `FileReader` allows us to read the data from file. Thanks to this classes, the program has
implemented database.

## Exceptions

`package exceptions` contains several exception classes that define exceptions tailored to specific situations. This
allows us to notify the user if the process did not complete successfully. All exceptions are caught in special block,
which make our program running. Exceptions inform user what was conducted wrongly.

## Running the application

You can run the application in `package app`. There is **App** class included. In this class you have reference do Cli
class where, as mentioned before, contains all the logic.