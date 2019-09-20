package edu.neu.entity;

import java.io.Serializable;

/**
 * ClassName:Disease
 * Package:edu.neu.entity
 * Description:
 *
 * @Date:2019/8/25 17:22
 * @Author:HetFrame
 */
public class Disease implements Serializable {
    protected Integer id;
    protected String DiseaseCode;
    protected String DiseaseName;
    protected String DiseaseICD;
    protected int DiseCategoryID;

    public Integer getId() {
        return id;
    }

    public String getDiseaseCode() {
        return DiseaseCode;
    }

    public String getDiseaseName() {
        return DiseaseName;
    }

    public String getDiseaseICD() {
        return DiseaseICD;
    }

    public int getDiseCategoryID() {
        return DiseCategoryID;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDiseaseCode(String diseaseCode) {
        DiseaseCode = diseaseCode;
    }

    public void setDiseaseName(String diseaseName) {
        DiseaseName = diseaseName;
    }

    public void setDiseaseICD(String diseaseICD) {
        DiseaseICD = diseaseICD;
    }

    public void setDiseCategoryID(int diseCategoryID) {
        DiseCategoryID = diseCategoryID;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "id=" + id +
                ", DiseaseCode='" + DiseaseCode + '\'' +
                ", DiseaseName='" + DiseaseName + '\'' +
                ", DiseaseICD='" + DiseaseICD + '\'' +
                ", DiseCategoryID=" + DiseCategoryID +
                '}';
    }
}
