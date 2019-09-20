package edu.neu.entity;

import java.io.Serializable;

/**
 * ClassName:prescription
 * Package:edu.neu.entity
 * Description:
 *
 * @Date:2019/8/25 17:07
 * @Author:HetFrame
 */
public class Prescription implements Serializable {
    private Integer id;
    private int medical_record_num;
    private int register_id;
    private int doctor;
    private int type;
    private String name;
    private String time;
    private int status;

    public Prescription() {
    }

    public Prescription(String name, int medical_record_num, int register_id, int doctor, int type, String time) {
        this.name = name;
        this.medical_record_num = medical_record_num;
        this.register_id = register_id;
        this.doctor = doctor;
        this.type = type;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public int getMedical_record_num() {
        return medical_record_num;
    }

    public int getRegister_id() {
        return register_id;
    }

    public int getDoctor() {
        return doctor;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMedical_record_num(int medical_record_num) {
        this.medical_record_num = medical_record_num;
    }

    public void setRegister_id(int register_id) {
        this.register_id = register_id;
    }

    public void setDoctor(int doctor) {
        this.doctor = doctor;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PrescriptionDAO{" +
                "id=" + id +
                ", medical_record_num=" + medical_record_num +
                ", register_id=" + register_id +
                ", doctor=" + doctor +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", status=" + status +
                '}';
    }
}
