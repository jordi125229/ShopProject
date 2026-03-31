package commandLine;

import money.Money;
import product.Computer;
import product.Electronics;
import product.Smartphone;

import java.util.Scanner;

public class DataReader {
    private Scanner scanner = new Scanner(System.in);

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

    public Smartphone readAndCreateSmartphone() {
        String smartphoneId = getDeviceId();
        System.out.println("Insert smartphone's name: ");
        String smartphoneName = scanner.nextLine();
        System.out.println("Insert price: ");
        String price = scanner.nextLine();
        System.out.println("Insert quantity: ");
        int quantity = scanner.nextInt();
        return new Smartphone(smartphoneId, smartphoneName, Money.of(price), quantity);
    }

    public Computer readAndCreateComputer() {
        String computerId = getDeviceId();
        System.out.println("Insert computer's name: ");
        String computerName = scanner.nextLine();
        System.out.println("Insert price: ");
        String price = scanner.nextLine();
        System.out.println("Insert quantity: ");
        int quantity = scanner.nextInt();
        return new Computer(computerId, computerName, Money.of(price), quantity);
    }

    public Electronics readAndCreateElectronic() {
        String deviceId = getDeviceId();
        System.out.println("Insert device's name: ");
        String electronicName = scanner.nextLine();
        System.out.println("Insert price: ");
        String price = scanner.nextLine();
        System.out.println("Insert quantity: ");
        int quantity = scanner.nextInt();
        return new Electronics(deviceId, electronicName, Money.of(price), quantity);
    }

    private String getDeviceId() {
        System.out.println("Insert id: ");
        return scanner.nextLine();
    }
}
