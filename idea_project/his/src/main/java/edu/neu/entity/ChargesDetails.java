package edu.neu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:ChargesDetails
 * Package:edu.neu.entity
 * Description:
 *
 * @Date:2019/9/7 18:07
 * @Author:HetFrame
 */
public class ChargesDetails implements Serializable {
    private Integer id;
    private int register_id;
    private int project;
    private int project_type;
    private String name;
    private double price;
    private int quantity;
    private Date time;
    private int user;
    private String charge_method;
    private int invoice;
    private int type;

    public ChargesDetails() {
    }

    public ChargesDetails(int register_id, int project, int project_type, String name, double price, int quantity, Date time, int user, String charge_method, int invoice, int type) {
        this.register_id = register_id;
        this.project = project;
        this.project_type = project_type;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.time = time;
        this.user = user;
        this.charge_method = charge_method;
        this.invoice = invoice;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRegister_id() {
        return register_id;
    }

    public void setRegister_id(int register_id) {
        this.register_id = register_id;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public int getProject_type() {
        return project_type;
    }

    public void setProject_type(int project_type) {
        this.project_type = project_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getCharge_method() {
        return charge_method;
    }

    public void setCharge_method(String charge_method) {
        this.charge_method = charge_method;
    }

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChargesDetails{" +
                "id=" + id +
                ", register_id=" + register_id +
                ", project=" + project +
                ", project_type=" + project_type +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", time=" + time +
                ", user=" + user +
                ", charge_method='" + charge_method + '\'' +
                ", invoice=" + invoice +
                ", type=" + type +
                '}';
    }
}
