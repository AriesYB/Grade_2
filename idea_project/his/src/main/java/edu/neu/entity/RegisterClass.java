package edu.neu.entity;

import java.io.Serializable;

/**
 * ClassName:RegisterClass
 * Package:edu.neu.entity
 * Description:
 *
 * @Date:2019/8/31 19:36
 * @Author:HetFrame
 */
public class RegisterClass implements Serializable {
    private Integer id;
    private String class_code;
    private String name;
    private int sequence_number;
    private double fee;
    private int register_quota;

    public Integer getId() {
        return id;
    }

    public String getClass_code() {
        return class_code;
    }

    public String getName() {
        return name;
    }

    public int getSequence_number() {
        return sequence_number;
    }

    public double getFee() {
        return fee;
    }

    public int getRegister_quota() {
        return register_quota;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSequence_number(int sequence_number) {
        this.sequence_number = sequence_number;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public void setRegister_quota(int register_quota) {
        this.register_quota = register_quota;
    }

    @Override
    public String toString() {
        return "RegisterClass{" +
                "id=" + id +
                ", class_code='" + class_code + '\'' +
                ", name='" + name + '\'' +
                ", sequence_number=" + sequence_number +
                ", fee=" + fee +
                ", register_quota=" + register_quota +
                '}';
    }
}
