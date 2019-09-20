package edu.neu.service;

import edu.neu.entity.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName:BaseDataService
 * Package:edu.neu.service
 * Description:
 *
 * @Date:2019/8/28 12:06
 * @Author:HetFrame
 */
public interface HisService {
    //挂号
    void register(RegisterPage registerPage);
    //查询所有科室
    List<Department> getAllDepartment();
    //查询所有用户
    List<User> getAllUser();
    //通过科室id查询用户
    List<User> getUserByDepartment(int department_id);
    //验证账号密码
    int checkAccountPassword(String account, String password);
    //获取可用发票号码
    int getInvoiceId();
    //获取可用病历号
    int getMedicalNum();
    //通过病历号获取病人信息
    Patient getPatient(int medicalNum);
    //通过发票号码删除发票信息
    void deleteInvoiceId(int id);
    //通过病历号删除病历信息
    void deleteMedicalNum(int id);
    //通过挂号等级查询挂号费用
    double getRegisterFee(int id);
    //通过医生username和日期查询病人病历信息
    Map<String, List<MedicalRecordPage>> getMedicalByDoctor(String username);
    //通过用户名查询用户
    User getUserByUsername(String username);
    //查询某医生当天的未诊患者或已诊患者
    List<Node> getPatientByStatus(int status);
    //获取病历信息
    MedicalRecordPage getMedicalRecordDetail(int id);
    //获取疾病信息
    List<Disease> getDiseaseDetail(List<String> list);
    //修改病历信息
    void updateMedicalRecord(MedicalRecordPage medicalRecordPage);
    List<DiseaseResult> getDiseaseDetailByName(String name);
    Prescription addPrescription(Prescription prescription);
    List<Prescription> getPrescriptionByMedicalInfo(int medicalNum,int registerId,int doctor,int type);
    void updatePrescriptionName(int id,String name);
    void deletePrescription(int id);
    void cancelPrescription(int id);
    void storePrescription(List<PrescriptionItem> list,int flag);
    List<PrescriptionItemPage> getByPrescriptionId(int id);
    void deletePrescriptionItem(int id);
    List<Drug> getDrugByName(String name);
    List<CostPage> getCostPage(int medicalNum);
    void settle(int invoice_id,String charge_method,List<Integer> ids);
}
