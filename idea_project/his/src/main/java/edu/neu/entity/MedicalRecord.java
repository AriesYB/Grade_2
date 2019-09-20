package edu.neu.entity;

import java.io.Serializable;

/**
 * ClassName:medical_record_num
 * Package:edu.neu.entity
 * Description:
 *
 * @Date:2019/8/25 16:59
 * @Author:HetFrame
 */
public class MedicalRecord implements Serializable {
    protected Integer id;
    protected int register_id;
    protected int medical_record_num;
    protected String chief_complaint;
    protected String current_treatment;
    protected String history_present_illness;
    protected String past_history;
    protected String allergic_history;
    protected String health_checkup;
    protected String primary_diagnosis;//诊断信息存疾病id1,id2_time1=time2 可为空
    protected int diagnosis_type;
    protected String checkup_advice;
    protected String announcements;
    protected int medical_record_status;

    private static MedicalRecord instance = null;

    private MedicalRecord() {
        setMedical_record_num(600600);
        setRegister_id(2);
        setChief_complaint("无");
        setHistory_present_illness("无");
        setPast_history("无");
        setAllergic_history("无");
        setHealth_checkup("无");
        setDiagnosis_type(0);
        setCheckup_advice("无");
        setAnnouncements("无");
        setCurrent_treatment("无");
        setMedical_record_status(1);
    }

    //给子类的构造方法
    protected MedicalRecord(String arg) {

    }

    /**
     * @Description 获取一个实例
     * @Param []
     * @Return edu.neu.entity.MedicalRecord
     */
    public static MedicalRecord getInstance() {
        if (instance == null) {
            instance = new MedicalRecord();
        }
        return instance;
    }

    public Integer getId() {
        return id;
    }

    public int getRegister_id() {
        return register_id;
    }

    public int getMedical_record_num() {
        return medical_record_num;
    }

    public String getChief_complaint() {
        return chief_complaint;
    }

    public String getHistory_present_illness() {
        return history_present_illness;
    }

    public String getPast_history() {
        return past_history;
    }

    public String getAllergic_history() {
        return allergic_history;
    }

    public String getHealth_checkup() {
        return health_checkup;
    }

    public String getPrimary_diagnosis() {
        return primary_diagnosis;
    }

    public int getDiagnosis_type() {
        return diagnosis_type;
    }

    public String getCheckup_advice() {
        return checkup_advice;
    }

    public String getAnnouncements() {
        return announcements;
    }

    public int getMedical_record_status() {
        return medical_record_status;
    }

    public String getCurrent_treatment() {
        return current_treatment;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRegister_id(int register_id) {
        this.register_id = register_id;
    }

    public void setMedical_record_num(int medical_record_num) {
        this.medical_record_num = medical_record_num;
    }

    public void setChief_complaint(String chief_complaint) {
        this.chief_complaint = chief_complaint;
    }

    public void setHistory_present_illness(String history_present_illness) {
        this.history_present_illness = history_present_illness;
    }

    public void setPast_history(String past_history) {
        this.past_history = past_history;
    }

    public void setAllergic_history(String allergic_history) {
        this.allergic_history = allergic_history;
    }

    public void setHealth_checkup(String health_checkup) {
        this.health_checkup = health_checkup;
    }

    public void setPrimary_diagnosis(String primary_diagnosis) {
        this.primary_diagnosis = primary_diagnosis;
    }

    public void setDiagnosis_type(int diagnosis_type) {
        this.diagnosis_type = diagnosis_type;
    }

    public void setCheckup_advice(String checkup_advice) {
        this.checkup_advice = checkup_advice;
    }

    public void setAnnouncements(String announcements) {
        this.announcements = announcements;
    }

    public void setMedical_record_status(int medical_record_status) {
        this.medical_record_status = medical_record_status;
    }

    public void setCurrent_treatment(String current_treatment) {
        this.current_treatment = current_treatment;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
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
