package edu.neu.entity;

/**
 * ClassName:DiseaseResult
 * Package:edu.neu.entity
 * Description: 类别为String 方便显示
 *
 * @Date:2019/9/3 9:50
 * @Author:HetFrame
 */
public class DiseaseResult extends Disease {
    private String DicaName;

    public String getDicaName() {
        return DicaName;
    }

    public void setDicaName(String DicaName) {
        this.DicaName = DicaName;
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
    public String toString() {
        return "DiseaseResult{" +
                "DicaName='" + DicaName + '\'' +
                ", id=" + id +
                ", DiseaseCode='" + DiseaseCode + '\'' +
                ", DiseaseName='" + DiseaseName + '\'' +
                ", DiseaseICD='" + DiseaseICD + '\'' +
                '}';
    }
}
