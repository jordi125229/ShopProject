package commandLine;

public enum Option {
    CREATE_SMARTPHONE(1, "Creating smartphone"),
    CREATE_COMPUTER(2, "Creating computer"),
    CREATE_ELECTRONIC(3, "Creating electronic device"),
    LIST_PRODUCTS(4, "Printing all products"),
    DELETE_ITEM(5, "Delete item"),
    SMARTPHONE_CONFIGURATION(6, "Smartphone's configuration"),
    COMPUTER_CONFIGURATION(7, "Computer's configuration"),
    ADD_TO_CART(8, "Initialize cart"),
    PRINT_CARTS(9, "Show cart"),
    ADD_ORDER(10, "Create order"),
    CREATE_INVOICE(11, "Invoice issuing"),
    PRINT_INVOICES(12, "View all invoices"),
    PRINT_ORDERS(13, "View all orders"),
    EXIT(14, "Closing app");

    private final int optionNumber;
    private final String description;

    Option(int optionNumber, String description) {
        this.optionNumber = optionNumber;
        this.description = description;
    }

    public static Option fromNumber(int number) {
        for (Option opt : Option.values()) {
            if (opt.optionNumber == number) {
                return opt;
            }
        }
        throw new IllegalArgumentException("Can't find this option!");
    }

    public static void printOptions() {
        System.out.println("Choose option by using numbers: ");
        Option[] values = Option.values();
        for (Option value : values) {
            System.out.println(value.optionNumber + "- " + value + "- " + value.description);
        }
    }
}

