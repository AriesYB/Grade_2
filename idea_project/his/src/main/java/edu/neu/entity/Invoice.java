package edu.neu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:Invoice
 * Package:edu.neu.entity
 * Description:
 *
 * @Date:2019/8/25 17:13
 * @Author:HetFrame
 */
public class Invoice implements Serializable {
    private Integer id;
    private int invoice_id;
    private double amount;
    private int status;
    private Date time;
    private int user;
    private int red_invoice;
    private static Invoice instance;

    private Invoice() {
    }

    /**
     * @Description 获取一个没有发票号码的实例
     * @Param []
     * @Return edu.neu.entity.Invoice
     */
    public static Invoice getInstance() {
        if (instance == null) {
            instance = new Invoice();
            instance.setAmount(0);
            instance.setStatus(1);
            instance.setTime(new Date());
            instance.setUser(0);
        }
        return instance;
    }

    public Integer getId() {
        return id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public double getAmount() {
        return amount;
    }

    public int getStatus() {
        return status;
    }

    public Date getTime() {
        return time;
    }

    public int getUser() {
        return user;
    }

    public int getRed_invoice() {
        return red_invoice;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setRed_invoice(int red_invoice) {
        this.red_invoice = red_invoice;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", invoice_id=" + invoice_id +
                ", amount=" + amount +
                ", status=" + status +
                ", time=" + time +
                ", user=" + user +
                ", red_invoice=" + red_invoice +
                '}';
    }
}
