package edu.neu.entity;

/**
 * ClassName:CostPage
 * Package:edu.neu.entity
 * Description:
 *
 * @Date:2019/9/7 17:17
 * @Author:HetFrame
 */
public class CostPage {
    private Integer id;
    private String name;
    private String ID_number;
    private String address;
    private int medical_record_num;
    private String project_name;
    private double price;
    private int quantity;
    private String time;
    private String status;

    public CostPage() {
    }

    public CostPage(String name, String ID_number, String address, int medical_record_num, String project_name, double price, int quantity, String time, String status) {
        this.name = name;
        this.ID_number = ID_number;
        this.address = address;
        this.medical_record_num = medical_record_num;
        this.project_name = project_name;
        this.price = price;
        this.quantity = quantity;
        this.time = time;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID_number() {
        return ID_number;
    }

    public void setID_number(String ID_number) {
        this.ID_number = ID_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMedical_record_num() {
        return medical_record_num;
    }

    public void setMedical_record_num(int medical_record_num) {
        this.medical_record_num = medical_record_num;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CostPage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ID_number='" + ID_number + '\'' +
                ", address='" + address + '\'' +
                ", medical_record_num=" + medical_record_num +
                ", project_name='" + project_name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
