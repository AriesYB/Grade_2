package edu.neu.dao;

import edu.neu.entity.MedicalRecordPage;
import edu.neu.entity.RegisterInfo;

import java.util.Date;
import java.util.List;

/**
 * ClassName:RegisterInfo
 * Package:edu.neu.dao
 * Description:
 *
 * @Date:2019/8/25 17:27
 * @Author:HetFrame
 */
public interface RegisterInfoDAO {
    List<RegisterInfo> findAll();

    RegisterInfo findById(int id);

    void insertRegisterInfo(RegisterInfo registerInfo);

    void updateRegisterInfo(RegisterInfo registerInfo);

    void deleteRegisterInfoById(int id);
    RegisterInfo findBySelf(String id_number,int docter_id,int medical_num);
    List<MedicalRecordPage> findByDoctorDate(int id, String date);
    MedicalRecordPage findByDoctorMedicalId(int doctor,int id,Date date);
}
