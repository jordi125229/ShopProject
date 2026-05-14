package repository;

import file.FileReader;
import file.FileWriter;
import payment.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRepository implements IInvoiceRepository {
    private List<Invoice> invoices;
    private FileReader fileReader;
    private FileWriter fileWriter;

    public InvoiceRepository() {
        this.invoices = new ArrayList<>();
        this.fileReader = new FileReader();
        this.fileWriter = new FileWriter();
    }

    @Override
    public List<Invoice> findAll() {
        fileReader.printAllInvoices();
        return invoices;
    }

    @Override
    public void addInvoice(Invoice invoice) {
        fileWriter.saveInvoiceToFile(invoice);
        invoices.add(invoice);
    }
}
