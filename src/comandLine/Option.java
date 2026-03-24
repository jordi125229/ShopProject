package comandLine;

public enum Option {
    CREATE_SMARTPHONE(1, "Creating smartphone"),
    CREATE_COMPUTER(2, "Creating computer"),
    CREATE_ELECTRONIC(3, "Creating electronic device"),
    LIST_PRODUCTS(4, "Printing all products"),
    SMARTPHONE_CONFIGURATION(5, "Smartphone's configuration"),
    COMPUTER_CONFIGURATION(6, "Computer's configuration"),
    INITIALIZE_CART(7, "Initialize cart"),
    PRINT_CARTS(8, "Show cart"),
    ADD_ORDER(9, "Create order"),
    CREATE_INVOICE(10, "Invoice issuing"),
    PRINT_INVOICES(11, "View all invoices"),
    EXIT(12, "Closing app");

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

