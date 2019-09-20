package edu.neu.dao;

import edu.neu.entity.Invoice;

/**
 * ClassName:InvoiceDAO
 * Package:edu.neu.dao
 * Description:
 *
 * @Date:2019/8/28 19:12
 * @Author:HetFrame
 */
public interface InvoiceDAO {
    Invoice findByInvoiceId(int id);
    void invalidInvoice(int id);
    void insertInvoice(Invoice invoice);
    void updateInvoice(Invoice invoice);
    void deleteInvoice(int id);
    void deleteByInvoiceId(int id);
    int newInvoice();
}
