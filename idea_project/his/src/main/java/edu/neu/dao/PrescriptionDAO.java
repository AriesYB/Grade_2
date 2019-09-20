package edu.neu.dao;

import edu.neu.entity.Prescription;

import java.util.List;

/**
 * ClassName:PrescriptionDAO
 * Package:edu.neu.dao
 * Description:
 *
 * @Date:2019/9/5 18:53
 * @Author:HetFrame
 */
public interface PrescriptionDAO {
    Prescription findById(int id);
    Prescription findBySelf(Prescription prescription);
    List<Prescription> findByMedicalInfo(int medicalNum, int registerId, int doctor, int type);
    void insertPrescription(Prescription prescription);
    void updatePrescription(int id,String name);
    void deletePrescription(int id);
    void cancelPrescription(int id);
    void estbPrescription(int id);
    List<Prescription> findByMedicalNumRegister(int medicalNum,int registerId);
}
