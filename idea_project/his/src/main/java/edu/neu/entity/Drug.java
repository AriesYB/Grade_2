package edu.neu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:Drugs
 * Package:edu.neu.entity
 * Description:
 *
 * @Date:2019/8/25 17:16
 * @Author:HetFrame
 */
public class Drug implements Serializable {
    private Integer id;
    private String DrugsCode;
    private String DrugsName;
    private String DrugsFormat;
    private String DrugsUnit;
    private String Manufacturer;
    private int DrugsDosageID;
    private int DrugsTypeID;
    private double DrugsPrice;
    private String MnemonicCode;
    private Date CreationDate;

    public Integer getId() {
        return id;
    }

    public String getDrugsCode() {
        return DrugsCode;
    }

    public String getDrugsName() {
        return DrugsName;
    }

    public String getDrugsFormat() {
        return DrugsFormat;
    }

    public String getDrugsUnit() {
        return DrugsUnit;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public int getDrugsDosageID() {
        return DrugsDosageID;
    }

    public int getDrugsTypeID() {
        return DrugsTypeID;
    }

    public double getDrugsPrice() {
        return DrugsPrice;
    }

    public String getMnemonicCode() {
        return MnemonicCode;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDrugsCode(String drugsCode) {
        DrugsCode = drugsCode;
    }

    public void setDrugsName(String drugsName) {
        DrugsName = drugsName;
    }

    public void setDrugsFormat(String drugsFormat) {
        DrugsFormat = drugsFormat;
    }

    public void setDrugsUnit(String drugsUnit) {
        DrugsUnit = drugsUnit;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public void setDrugsDosageID(int drugsDosageID) {
        DrugsDosageID = drugsDosageID;
    }

    public void setDrugsTypeID(int drugsTypeID) {
        DrugsTypeID = drugsTypeID;
    }

    public void setDrugsPrice(double drugsPrice) {
        DrugsPrice = drugsPrice;
    }

    public void setMnemonicCode(String mnemonicCode) {
        MnemonicCode = mnemonicCode;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", DrugsCode='" + DrugsCode + '\'' +
                ", DrugsName='" + DrugsName + '\'' +
                ", DrugsFormat='" + DrugsFormat + '\'' +
                ", DrugsUnit='" + DrugsUnit + '\'' +
                ", Manufacturer='" + Manufacturer + '\'' +
                ", DrugsDosageID=" + DrugsDosageID +
                ", DrugsTypeID=" + DrugsTypeID +
                ", DrugsPrice=" + DrugsPrice +
                ", MnemonicCode='" + MnemonicCode + '\'' +
                ", CreationDate=" + CreationDate +
                '}';
    }
}
