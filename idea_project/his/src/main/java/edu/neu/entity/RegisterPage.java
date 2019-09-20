package edu.neu.entity;

/**
 * ClassName:RegisterInfo
 * Package:edu.neu.entity
 * Description:
 *
 * @Date:2019/8/27 10:18
 * @Author:HetFrame
 */
public class RegisterPage {
    private int invoice_id;
    private int medical_record_num;
    private String name;
    private int sex;
    private int age;
    private String unit;
    private String date_birth;
    private String settlement_type;
    private String id_number;
    private String address;
    private int department;
    private int register_class;
    private int doctor;
    private int is_need_paper;
    private String charge_method;
    private double amount;

    public int getInvoice_id() {
        return invoice_id;
    }

    public int getMedical_record_num() {
        return medical_record_num;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSettlement_type() {
        return settlement_type;
    }

    public String getId_number() {
        return id_number;
    }

    public String getAddress() {
        return address;
    }

    public int getDepartment() {
        return department;
    }

    public int getRegister_class() {
        return register_class;
    }

    public int getDoctor() {
        return doctor;
    }

    public String getCharge_method() {
        return charge_method;
    }

    public double getAmount() {
        return amount;
    }

    public int getSex() {
        return sex;
    }

    public String getUnit() {
        return unit;
    }

    public String getDate_birth() {
        return date_birth;
    }

    public int getIs_need_paper() {
        return is_need_paper;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public void setMedical_record_num(int medical_record_num) {
        this.medical_record_num = medical_record_num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
    }

    public void setSettlement_type(String settlement_type) {
        this.settlement_type = settlement_type;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setRegister_class(int register_class) {
        this.register_class = register_class;
    }

    public void setDoctor(int doctor) {
        this.doctor = doctor;
    }

    public void setIs_need_paper(int is_need_paper) {
        this.is_need_paper = is_need_paper;
    }

    public void setCharge_method(String charge_method) {
        this.charge_method = charge_method;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "RegisterPage{" +
                "invoice_id=" + invoice_id +
                ", medical_record_num=" + medical_record_num +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", unit='" + unit + '\'' +
                ", date_birth=" + date_birth +
                ", settlement_type='" + settlement_type + '\'' +
                ", id_number='" + id_number + '\'' +
                ", address='" + address + '\'' +
                ", department=" + department +
                ", register_class=" + register_class +
                ", doctor=" + doctor +
                ", is_need_paper=" + is_need_paper +
                ", charge_method='" + charge_method + '\'' +
                ", amount=" + amount +
                '}';
    }
}
