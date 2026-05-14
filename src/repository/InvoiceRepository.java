package repository;

import payment.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRepository implements IInvoiceRepository {
    private List<Invoice> invoices;

    public InvoiceRepository() {
        this.invoices = new ArrayList<>();
    }

    @Override
    public List<Invoice> findAll() {
        return invoices;
    }

    @Override
    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }
}
