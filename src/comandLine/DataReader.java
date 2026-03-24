package comandLine;

import money.Money;
import product.Computer;
import product.Electronics;
import product.Smartfon;
import java.util.Scanner;

public class DataReader {
    Scanner scanner = new Scanner(System.in);

    public int getAndReturnInt() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine();
        }
    }

    public String getString() {
        return scanner.nextLine();
    }

    public Smartfon readAndCreateSmartphone() {
        System.out.println("Insert id: ");
        String smartphoneId = scanner.nextLine();
        System.out.println("Insert smartphone's name: ");
        String smartphoneName = scanner.nextLine();
        System.out.println("Insert price: ");
        String price = scanner.nextLine();
        System.out.println("Insert quantity: ");
        int quantity = scanner.nextInt();
        return new Smartfon(smartphoneId, smartphoneName, Money.of(price), quantity);
    }

    public Computer readAndCreateComputer() {
        System.out.println("Insert id: ");
        String computerId = scanner.nextLine();
        System.out.println("Insert computer's name: ");
        String computerName = scanner.nextLine();
        System.out.println("Insert price: ");
        String price = scanner.nextLine();
        System.out.println("Insert quantity: ");
        int quantity = scanner.nextInt();
        return new Computer(computerId, computerName, Money.of(price), quantity);
    }

    public Electronics readAndCreateElectronic() {
        System.out.println("Insert id: ");
        String computerId = scanner.nextLine();
        System.out.println("Insert device's name: ");
        String computerName = scanner.nextLine();
        System.out.println("Insert price: ");
        String price = scanner.nextLine();
        System.out.println("Insert quantity: ");
        int quantity = scanner.nextInt();
        return new Electronics(computerId, computerName, Money.of(price), quantity);
    }
}
