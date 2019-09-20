package edu.neu.entity;

/**
 * ClassName:PrescriptionItemPage
 * Package:edu.neu.entity
 * Description:
 *
 * @Date:2019/9/6 20:35
 * @Author:HetFrame
 */
public class PrescriptionItemPage extends PrescriptionItem {
    private String DrugsName;
    private String DrugsFormat;
    private double DrugsPrice;

    public PrescriptionItemPage() {
    }

    public PrescriptionItemPage(PrescriptionItem prescriptionItem) {
        setConsumption(prescriptionItem.getConsumption());
        setDrug(prescriptionItem.getDrug());
        setFrequency(prescriptionItem.getFrequency());
        setQuantity(prescriptionItem.getQuantity());
        setUsage(prescriptionItem.getUsage());
    }

    public double getDrugsPrice() {
        return DrugsPrice;
    }

    public void setDrugsPrice(double drugsPrice) {
        this.DrugsPrice = drugsPrice;
    }

    public String getDrugsName() {
        return DrugsName;
    }

    public String getDrugsFormat() {
        return DrugsFormat;
    }

    public void setDrugsName(String drugsName) {
        this.DrugsName = drugsName;
    }

    public void setDrugsFormat(String drugsFormat) {
        this.DrugsFormat = drugsFormat;
    }

    @Override
    public int getPrescription_id() {
        return super.getPrescription_id();
    }

    @Override
    public void setPrescription_id(int prescription_id) {
        super.setPrescription_id(prescription_id);
    }

    @Override
    public int getDrug() {
        return super.getDrug();
    }

    @Override
    public String getUsage() {
        return super.getUsage();
    }

    @Override
    public String getConsumption() {
        return super.getConsumption();
    }

    @Override
    public String getFrequency() {
        return super.getFrequency();
    }

    @Override
    public int getQuantity() {
        return super.getQuantity();
    }

    @Override
    public void setDrug(int drug) {
        super.setDrug(drug);
    }

    @Override
    public void setUsage(String usage) {
        super.setUsage(usage);
    }

    @Override
    public void setConsumption(String consumption) {
        super.setConsumption(consumption);
    }

    @Override
    public void setFrequency(String frequency) {
        super.setFrequency(frequency);
    }

    @Override
    public void setQuantity(int quantity) {
        super.setQuantity(quantity);
    }

    @Override
    public String toString() {
        return "PrescriptionItemPage{" +
                "drugName='" + DrugsName + '\'' +
                ", drugFormat='" + DrugsFormat + '\'' +
                ", price=" + DrugsPrice +
                ", prescription_id=" + prescription_id +
                ", drug=" + drug +
                ", usage='" + usage + '\'' +
                ", consumption='" + consumption + '\'' +
                ", frequency='" + frequency + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
