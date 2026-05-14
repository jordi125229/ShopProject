package payment;

import client.Client;
import money.Money;

import java.time.LocalDateTime;

public class Invoice {
    private String invoiceNumber;
    private LocalDateTime issueDate;
    private Client client;
    private Money total;
    private String itemDescription;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public LocalDateTime getIssueDate() {
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

    public void setIssueDate(LocalDateTime issueDate) {
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
