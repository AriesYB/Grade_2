package edu.neu.dao;

import edu.neu.entity.MedicalRecord;

/**
 * ClassName:MedicalRecordDAO
 * Package:edu.neu.dao
 * Description:
 *
 * @Date:2019/8/28 20:06
 * @Author:HetFrame
 */
public interface MedicalRecordDAO {
    MedicalRecord findById(int id);
    void insertMedicalRecord(MedicalRecord medicalRecord);
    void updateMedicalRecord(MedicalRecord medicalRecord);
    void deleteMedicalRecord(int id);
    void deleteByMedicalNum(int id);
    int newMedicalNum();
    MedicalRecord findByMedicalNum(int id);
}
