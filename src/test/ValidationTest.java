package test;

import client.Client;
import commandLine.ConsolePrinter;
import commandLine.DataReader;
import dataValidation.Validator;

public class ValidationTest {
    private DataReader dataReader = new DataReader();
    private ConsolePrinter consolePrinter = new ConsolePrinter();
    Validator validator = new Validator();

    public static void main(String[] args) {
        ValidationTest test = new ValidationTest();
        Client clientByProvidingData = test.getClientByProvidingData();
        System.out.println(clientByProvidingData);
    }

    private Client getClientByProvidingData() {
        String clientName = getValidatedName("Insert client's name: ");
        String lastName = getValidatedName("Insert client's lastname: ");
        consolePrinter.printLine("Insert client's id: ");
        String id = dataReader.getString();
        return new Client(clientName, lastName, id);
    }

    private String getValidatedName(String prompt) {
        String name;
        while (true) {
            consolePrinter.printLine(prompt);
            name = dataReader.getString();
            if (validator.ifNameIsValid(name)) {
                return validator.firstLetterUpperCase(name);
            }
            consolePrinter.printLine("Invalid input! Try again.");
        }
    }
}
