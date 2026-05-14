package payment;

import client.Client;
import lombok.Data;
import money.Money;

import java.time.ZonedDateTime;

@Data
public class Invoice {
    private String invoiceNumber;
    private ZonedDateTime issueDate;
    private Client client;
    private Money total;
    private String itemDescription;

}
