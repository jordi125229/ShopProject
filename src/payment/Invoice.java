package payment;

import client.Client;
import money.Money;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Invoice {
    private String invoiceNumber;
    private ZonedDateTime issueDate;
    private Client client;
    private Money total;
    private String itemDescription;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public ZonedDateTime getIssueDate() {
        return issueDate;
    }

    public Client getClient() {
        return client;
    }

    public Money getTotal() {
        return total;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setIssueDate(ZonedDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setTotal(Money total) {
        this.total = total;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", issueDate=" + issueDate +
                ", client=" + client +
                ", total=" + total +
                ", itemDescription='" + itemDescription + "}";
    }
}
