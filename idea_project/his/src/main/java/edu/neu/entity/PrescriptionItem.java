package edu.neu.entity;

import java.io.Serializable;

/**
 * ClassName:PrescriptionItem
 * Package:edu.neu.entity
 * Description:
 *
 * @Date:2019/9/6 11:43
 * @Author:HetFrame
 */
public class PrescriptionItem implements Serializable {
    private Integer id;
    protected int prescription_id;
    protected int drug;
    protected String usage;
    protected String consumption;
    protected String frequency;
    protected int quantity;
    private int charge_status;
    private int status;

    public PrescriptionItem() {
    }

    public PrescriptionItem(PrescriptionItemPage prescriptionItemPage) {
        setDrug(prescriptionItemPage.getDrug());
        setUsage(prescriptionItemPage.getUsage());
        setConsumption(prescriptionItemPage.getConsumption());
        setFrequency(prescriptionItemPage.getFrequency());
        setQuantity(prescriptionItemPage.getQuantity());
        setCharge_status(1);
        setStatus(1);
        setPrescription_id(prescriptionItemPage.getPrescription_id());
    }

    public Integer getId() {
        return id;
    }

    public int getPrescription_id() {
        return prescription_id;
    }

    public int getDrug() {
        return drug;
    }

    public String getUsage() {
        return usage;
    }

    public String getConsumption() {
        return consumption;
    }

    public String getFrequency() {
        return frequency;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCharge_status() {
        return charge_status;
    }

    public int getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrescription_id(int prescription_id) {
        this.prescription_id = prescription_id;
    }

    public void setDrug(int drug) {
        this.drug = drug;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCharge_status(int charge_status) {
        this.charge_status = charge_status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PrescriptionItem{" +
                "id=" + id +
                ", prescription_id=" + prescription_id +
                ", drug=" + drug +
                ", usage='" + usage + '\'' +
                ", consumption=" + consumption +
                ", frequency='" + frequency + '\'' +
                ", quantity=" + quantity +
                ", charge_status=" + charge_status +
                ", status=" + status +
                '}';
    }
}
