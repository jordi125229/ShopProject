package repository;

import payment.Invoice;
import java.util.List;

public interface IInvoiceRepository {

    List<Invoice> findAll();

    void addInvoice(Invoice invoice);
}
