package repository;

import order.Order;
import payment.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRepository {
    private List<Invoice> invoices;

    public InvoiceRepository() {
        this.invoices = new ArrayList<>();
    }

    public List<Invoice> findAll() {
        return invoices;
    }
}
