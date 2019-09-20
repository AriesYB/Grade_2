package edu.neu.entity;

/**
 * ClassName:DiseaseDetailPage
 * Package:edu.neu.entity
 * Description:
 *
 * @Date:2019/9/2 22:34
 * @Author:HetFrame
 */
public class DiseaseDetailPage extends Disease{
    private String time;
    public DiseaseDetailPage(Disease disease, String time){
        setId(disease.getId());
        setDiseCategoryID(disease.getDiseCategoryID());
        setDiseaseName(disease.getDiseaseName());
        setDiseaseCode(disease.getDiseaseCode());
        setDiseaseICD(disease.getDiseaseICD());
        this.time = time;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public String getDiseaseCode() {
        return super.getDiseaseCode();
    }

    @Override
    public String getDiseaseName() {
        return super.getDiseaseName();
    }

    @Override
    public String getDiseaseICD() {
        return super.getDiseaseICD();
    }

    @Override
    public int getDiseCategoryID() {
        return super.getDiseCategoryID();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public void setDiseaseCode(String diseaseCode) {
        super.setDiseaseCode(diseaseCode);
    }

    @Override
    public void setDiseaseName(String diseaseName) {
        super.setDiseaseName(diseaseName);
    }

    @Override
    public void setDiseaseICD(String diseaseICD) {
        super.setDiseaseICD(diseaseICD);
    }

    @Override
    public void setDiseCategoryID(int diseCategoryID) {
        super.setDiseCategoryID(diseCategoryID);
    }

    @Override
    public String toString() {
        return "DiseaseDetailPage{" +
                "time='" + time + '\'' +
                ", id=" + id +
                ", DiseaseCode='" + DiseaseCode + '\'' +
                ", DiseaseName='" + DiseaseName + '\'' +
                ", DiseaseICD='" + DiseaseICD + '\'' +
                ", DiseCategoryID=" + DiseCategoryID +
                '}';
    }
}
