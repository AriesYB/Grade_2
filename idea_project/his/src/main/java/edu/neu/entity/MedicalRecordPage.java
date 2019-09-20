package edu.neu.entity;


import java.io.Serializable;

/**
 * ClassName:MedicalRecordPage
 * Package:edu.neu.entity
 * Description: 继承病历对象用于页面数据
 *
 * @Date:2019/9/1 10:07
 * @Author:HetFrame
 */
public class MedicalRecordPage extends MedicalRecord implements Serializable {
    private String name;
    private int sex;
    private int age;
    private String ID_number;
    private int status;//register_info里的status

    public MedicalRecordPage() {
        super(null);
    }

    public String getName() {
        return name;
    }

    public String getID_number() {
        return ID_number;
    }

    public int getStatus() {
        return status;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setID_number(String ID_number) {
        this.ID_number = ID_number;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getCurrent_treatment() {
        return super.getCurrent_treatment();
    }

    @Override
    public void setCurrent_treatment(String current_treatment) {
        super.setCurrent_treatment(current_treatment);
    }

    @Override
    public Integer getId() {//病历信息的id 不是病历号
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }
    @Override
    public int getRegister_id() {
        return super.getRegister_id();
    }

    @Override
    public int getMedical_record_num() {
        return super.getMedical_record_num();
    }

    @Override
    public String getChief_complaint() {
        return super.getChief_complaint();
    }

    @Override
    public String getHistory_present_illness() {
        return super.getHistory_present_illness();
    }

    @Override
    public String getPast_history() {
        return super.getPast_history();
    }

    @Override
    public String getAllergic_history() {
        return super.getAllergic_history();
    }

    @Override
    public String getHealth_checkup() {
        return super.getHealth_checkup();
    }

    @Override
    public String getPrimary_diagnosis() {
        return super.getPrimary_diagnosis();
    }

    @Override
    public int getDiagnosis_type() {
        return super.getDiagnosis_type();
    }

    @Override
    public String getCheckup_advice() {
        return super.getCheckup_advice();
    }

    @Override
    public String getAnnouncements() {
        return super.getAnnouncements();
    }

    @Override
    public int getMedical_record_status() {
        return super.getMedical_record_status();
    }

    @Override
    public void setRegister_id(int register_id) {
        super.setRegister_id(register_id);
    }

    @Override
    public void setMedical_record_num(int medical_record_num) {
        super.setMedical_record_num(medical_record_num);
    }

    @Override
    public void setChief_complaint(String chief_complaint) {
        super.setChief_complaint(chief_complaint);
    }

    @Override
    public void setHistory_present_illness(String history_present_illness) {
        super.setHistory_present_illness(history_present_illness);
    }

    @Override
    public void setPast_history(String past_history) {
        super.setPast_history(past_history);
    }

    @Override
    public void setAllergic_history(String allergic_history) {
        super.setAllergic_history(allergic_history);
    }

    @Override
    public void setHealth_checkup(String health_checkup) {
        super.setHealth_checkup(health_checkup);
    }

    @Override
    public void setPrimary_diagnosis(String primary_diagnosis) {
        super.setPrimary_diagnosis(primary_diagnosis);
    }

    @Override
    public void setDiagnosis_type(int diagnosis_type) {
        super.setDiagnosis_type(diagnosis_type);
    }

    @Override
    public void setCheckup_advice(String checkup_advice) {
        super.setCheckup_advice(checkup_advice);
    }

    @Override
    public void setAnnouncements(String announcements) {
        super.setAnnouncements(announcements);
    }

    @Override
    public void setMedical_record_status(int medical_record_status) {
        super.setMedical_record_status(medical_record_status);
    }

    @Override
    public String toString() {
        return "MedicalRecordPage{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", ID_number='" + ID_number + '\'' +
                ", status=" + status +
                ", id=" + id +
                ", register_id=" + register_id +
                ", medical_record_num=" + medical_record_num +
                ", chief_complaint='" + chief_complaint + '\'' +
                ", current_treatment='" + current_treatment + '\'' +
                ", history_present_illness='" + history_present_illness + '\'' +
                ", past_history='" + past_history + '\'' +
                ", allergic_history='" + allergic_history + '\'' +
                ", health_checkup='" + health_checkup + '\'' +
                ", primary_diagnosis='" + primary_diagnosis + '\'' +
                ", diagnosis_type=" + diagnosis_type +
                ", checkup_advice='" + checkup_advice + '\'' +
                ", announcements='" + announcements + '\'' +
                ", medical_record_status=" + medical_record_status +
                '}';
    }
}
