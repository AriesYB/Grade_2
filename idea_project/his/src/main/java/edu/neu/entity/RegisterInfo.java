package edu.neu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:RegisterInfo
 * Package:edu.neu.entity
 * Description:
 *
 * @Date:2019/8/24 9:48
 * @Author:HetFrame
 */
public class RegisterInfo implements Serializable {
    private Integer id;
    private String ID_number;
    private int medical_record_num;
    private String settlement_type;
    private int register_class;
    private int department;
    private int doctor;
    private int is_need_book;
    private Date time;
    private String see_doctor_time;
    private String am_or_pm;
    private int status = 1;
    private int register_user;

    public Integer getId() {
        return id;
    }

    public String getID_number() {
        return ID_number;
    }

    public int getMedical_record_num() {
        return medical_record_num;
    }

    public String getSettlement_type() {
        return settlement_type;
    }

    public int getRegister_class() {
        return register_class;
    }

    public int getDepartment() {
        return department;
    }

    public int getDoctor() {
        return doctor;
    }

    public int getIs_need_book() {
        return is_need_book;
    }

    public Date getTime() {
        return time;
    }

    public String getSee_doctor_time() {
        return see_doctor_time;
    }

    public String getAm_or_pm() {
        return am_or_pm;
    }

    public int getStatus() {
        return status;
    }

    public int getRegister_user() {
        return register_user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setID_number(String ID_number) {
        this.ID_number = ID_number;
    }

    public void setMedical_record_num(int medical_record_num) {
        this.medical_record_num = medical_record_num;
    }

    public void setSettlement_type(String settlement_type) {
        this.settlement_type = settlement_type;
    }

    public void setRegister_class(int register_class) {
        this.register_class = register_class;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setDoctor(int doctor) {
        this.doctor = doctor;
    }

    public void setIs_need_book(int is_need_book) {
        this.is_need_book = is_need_book;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setSee_doctor_time(String see_doctor_time) {
        this.see_doctor_time = see_doctor_time;
    }

    public void setAm_or_pm(String am_or_pm) {
        this.am_or_pm = am_or_pm;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setRegister_user(int register_user) {
        this.register_user = register_user;
    }

    @Override
    public String toString() {
        return "RegisterInfo{" +
                "id=" + id +
                ", ID_number='" + ID_number + '\'' +
                ", medical_record_num=" + medical_record_num +
                ", settlement_type='" + settlement_type + '\'' +
                ", register_class=" + register_class +
                ", department=" + department +
                ", doctor=" + doctor +
                ", is_need_book=" + is_need_book +
                ", time=" + time +
                ", see_doctor_time=" + see_doctor_time +
                ", am_or_pm='" + am_or_pm + '\'' +
                ", status=" + status +
                ", register_user=" + register_user +
                '}';
    }
}
