package edu.neu.serviceImpl;

import edu.neu.dao.*;
import edu.neu.entity.*;
import edu.neu.service.HisService;
import edu.neu.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * ClassName:HisServiceImpl
 * Package:edu.neu.serviceImpl
 * Description:
 *
 * @Date:2019/8/28 12:11
 * @Author:HetFrame
 */
@Service("hisService")
public class HisServiceImpl implements HisService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private DepartmentDAO departmentDAO;
    @Autowired
    private InvoiceDAO invoiceDAO;
    @Autowired
    private MedicalRecordDAO medicalRecordDAO;
    @Autowired
    private PatientDAO patientDAO;
    @Autowired
    private RegisterInfoDAO registerInfoDAO;
    @Autowired
    private RegisterClassDAO registerClassDAO;
    @Autowired
    private DiseaseDAO diseaseDAO;
    @Autowired
    private PrescriptionDAO prescriptionDAO;
    @Autowired
    private PrescriptionItemDAO prescriptionItemDAO;
    @Autowired
    private DrugDAO drugDAO;
    @Autowired
    private ChargesDetailDAO chargesDetailDAO;

    @Override
    @Transactional
    public void register(RegisterPage registerPage) {
        Patient patient = new Patient();
        patient.setAddress(registerPage.getAddress());
        patient.setAge(registerPage.getAge());
        patient.setDate_birth(registerPage.getDate_birth());
        patient.setID_number(registerPage.getId_number());
        patient.setName(registerPage.getName());
        patient.setSex(registerPage.getSex());
        Patient old_patient = patientDAO.findById_number(registerPage.getId_number());
        if (old_patient == null) {//无旧数据
            patientDAO.insertPatient(patient);
        } else if (!old_patient.equals(patient)) {//如果数据不一致
            patientDAO.deletePatientById(old_patient.getId());//删除旧数据
            patientDAO.insertPatient(patient);
        }
        RegisterInfo registerInfo = new RegisterInfo();
        registerInfo.setID_number(registerPage.getId_number());
        registerInfo.setMedical_record_num(registerPage.getMedical_record_num());
        registerInfo.setSettlement_type(registerPage.getSettlement_type());
        registerInfo.setRegister_class(registerPage.getRegister_class());
        registerInfo.setDepartment(registerPage.getDepartment());
        registerInfo.setDoctor(registerPage.getDoctor());
        registerInfo.setIs_need_book(registerPage.getIs_need_paper());
        registerInfo.setTime(new Date());
        //获取用户
        User user = (User) Util.currentRequest().getSession().getAttribute("user");
        registerInfo.setRegister_user(user.getId());
        registerInfo.setSee_doctor_time(Util.getDateString());//TODO 要根据医生排班情况以及已挂号患者来处理 暂时使用挂号当天
        registerInfoDAO.insertRegisterInfo(registerInfo);//插入新信息
        RegisterInfo registerInfo1 = registerInfoDAO.findBySelf(registerPage.getId_number(), registerPage.getDoctor(), registerPage.getMedical_record_num());
        //更新发票信息
        Invoice invoice = (Invoice) Util.currentRequest().getSession().getAttribute("invoiceId");
        invoice.setAmount(registerPage.getAmount());
        invoice.setUser(user.getId());
        invoice.setTime(new Date());
        invoiceDAO.updateInvoice(invoice);
        //更新病历信息中的挂号id
        MedicalRecord medicalRecord = (MedicalRecord) Util.currentRequest().getSession().getAttribute("medicalNum");
        medicalRecord.setRegister_id(registerInfo1.getId());
        medicalRecordDAO.updateMedicalRecord(medicalRecord);
    }

    /**
     * @Description 获取所有用户
     * @Param []
     * @Return java.util.List<edu.neu.entity.User>
     */
    @Override
    public List<User> getAllUser() {
        return userDAO.findAll();
    }

    /**
     * @Description 获取所有科室
     * @Param []
     * @Return java.util.List<edu.neu.entity.Department>
     */
    @Override
    public List<Department> getAllDepartment() {
        return departmentDAO.findAll();
    }

    /**
     * @Description 获取科室的所有医生
     * @Param [department_id]
     * @Return java.util.List<edu.neu.entity.User>
     */
    @Override
    public List<User> getUserByDepartment(int department_id) {
        return userDAO.findByDepartment(department_id);
    }

    /**
     * @Description 检验账号密码
     * @Param [account, password]
     * @Return int
     */
    @Override
    public int checkAccountPassword(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user == null) {//用户名不存在
            return -1;
        }
        if (user.getPassword().equals(password)) {//密码正确
            Util.currentRequest().getSession(true).setAttribute("user", user);
            return 1;
        }
        return 0;//密码错误
    }

    /**
     * @Description 获取最新发票号码 获取实例设置最大的号码再插入返回号码
     * @Param []
     * @Return int
     */
    @Override
    public int getInvoiceId() {
        //从session里获取
        HttpSession session = Util.currentRequest().getSession();
        Invoice invoice = (Invoice) session.getAttribute("invoiceId");
        if (invoice == null) {
            //再从map里获取
            Map<String, ArrayDeque> stringArrayDequeMap = Util.getMap();
            ArrayDeque<Invoice> arrayDeque = stringArrayDequeMap.get("invoiceId");
            invoice = arrayDeque.pollFirst();
            if (invoice == null) {
                //再从数据库获取
                invoice = Invoice.getInstance();
                invoice.setInvoice_id(invoiceDAO.newInvoice());
                invoiceDAO.insertInvoice(invoice);
                invoice = invoiceDAO.findByInvoiceId(invoice.getInvoice_id());
            }
            //将当前使用的发票号放入session
            session.setAttribute("invoiceId", invoice);
        }
        return invoice.getInvoice_id();
    }

    /**
     * @Description 获取最新病历号码 原理同发票号码
     * @Param []
     * @Return int
     */
    @Override
    public int getMedicalNum() {
        //从session里获取
        HttpSession session = Util.currentRequest().getSession();
        MedicalRecord medicalRecord = (MedicalRecord) session.getAttribute("medicalNum");
        if (medicalRecord == null) {
            Map<String, ArrayDeque> stringArrayDequeMap = Util.getMap();
            ArrayDeque<MedicalRecord> arrayDeque = stringArrayDequeMap.get("medicalNum");
            medicalRecord = arrayDeque.pollFirst();
            if (medicalRecord == null) {
                medicalRecord = MedicalRecord.getInstance();
                medicalRecord.setMedical_record_num(medicalRecordDAO.newMedicalNum());
                medicalRecordDAO.insertMedicalRecord(medicalRecord);
                medicalRecord = medicalRecordDAO.findByMedicalNum(medicalRecord.getMedical_record_num());
            }
            //将当前使用病历号放入session
            session.setAttribute("medicalNum", medicalRecord);
        }
        return medicalRecord.getMedical_record_num();
    }

    /**
     * @Description 通过病历号查找患者
     * @Param [medicalNum]
     * @Return edu.neu.entity.Patient
     */

    @Override
    public Patient getPatient(int medicalNum) {
        Patient patient = null;
        MedicalRecord medicalRecord = medicalRecordDAO.findByMedicalNum(medicalNum);
        if (medicalRecord == null) {
            return patient;
        }
        RegisterInfo registerInfo = registerInfoDAO.findById(medicalRecord.getRegister_id());//用病历的挂号查找挂号信息
        if (registerInfo == null) {
            return patient;
        }
        patient = patientDAO.findById_number(registerInfo.getID_number());//用挂号信息中的身份证查找病人
        return patient;
    }

    /**
     * @Description 删除未使用的发票
     * @Param [id]
     * @Return void
     */
    @Override
    public void deleteInvoiceId(int id) {
        invoiceDAO.deleteByInvoiceId(id);
    }

    /**
     * @Description 删除未使用的病历号
     * @Param [id]
     * @Return void
     */
    @Override
    public void deleteMedicalNum(int id) {
        medicalRecordDAO.deleteByMedicalNum(id);
    }

    /**
     * @Description 通过id查询挂号级别费用
     * @Param [id]
     * @Return edu.neu.entity.RegisterClass
     */
    @Override
    public double getRegisterFee(int id) {
        return registerClassDAO.findById(id).getFee();
    }


    /**
     * @Description 通过医生id查询它的患者
     * @Param [id]
     * @Return java.util.List<edu.neu.entity.Patient>
     */

    @Override
    public Map<String, List<MedicalRecordPage>> getMedicalByDoctor(String username) {
        User user = getUserByUsername(username);
        //获取该医生当天看诊的病人病历信息  直接查询当天的
        List<MedicalRecordPage> list = registerInfoDAO.findByDoctorDate(user.getId(), Util.getDateString());
        List<MedicalRecordPage> list1 = new ArrayList<>();
        List<MedicalRecordPage> list2 = new ArrayList<>();
        for (MedicalRecordPage medicalRecordPage : list
        ) {
            if (medicalRecordPage.getStatus() == 1) {//未诊
                list1.add(medicalRecordPage);
            } else if (medicalRecordPage.getStatus() == 2) {//已诊
                list2.add(medicalRecordPage);
            }
        }
        Map<String, List<MedicalRecordPage>> map = new HashMap<>();
        map.put("weizhen", list1);
        map.put("yizhen", list2);
        return map;
    }

    /**
     * @Description 通过用户名查询医生
     * @Param [username]
     * @Return edu.neu.entity.User
     */

    @Override
    public User getUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    /**
     * @Description 获取未诊或者已诊的病人信息
     * @Param [status]
     * @Return java.util.List<edu.neu.entity.MedicalRecordPage>
     */
    @Override
    public List<Node> getPatientByStatus(int status) {
        User user = (User) Util.currentRequest().getSession().getAttribute("user");
        List<MedicalRecordPage> list = registerInfoDAO.findByDoctorDate(user.getId(), Util.getDateString());
        List<Node> list1 = new ArrayList<>();
        int i = 10 + status;//pId
        int j = (i * 10) * 10;//id
        int n = 1;
        for (MedicalRecordPage medicalRecordPage : list) {
            if (medicalRecordPage.getStatus() == status) {
                list1.add(new Node(j + n, i, medicalRecordPage.getName(), "false", "false", "system-base.html?param=" + medicalRecordPage.getId()));
            }
            n++;
        }
        return list1;
    }

    /**
     * @Description 根据病历id获取具体病人信息
     * @Param [id]
     * @Return edu.neu.entity.MedicalRecordPage
     */
    @Override
    public MedicalRecordPage getMedicalRecordDetail(int id) {
        User user = (User) Util.currentRequest().getSession().getAttribute("user");
        return registerInfoDAO.findByDoctorMedicalId(user.getId(), id, Util.getDateTime());
    }

    /**
     * @Description 根据诊断获取疾病信息
     * @Param []
     * @Return java.util.List<edu.neu.entity.Disease>
     */
    @Override
    public List<Disease> getDiseaseDetail(List<String> list) {
        List<Disease> list1 = new ArrayList<>();
        for (String icd : list
        ) {
            list1.add(diseaseDAO.findByICD(icd));
        }
        return list1;
    }

    /**
     * @Description 更新病历
     * @Param [medicalRecordPage]
     * @Return void
     */
    @Override
    public void updateMedicalRecord(MedicalRecordPage medicalRecordPage) {
        //更新病历信息
        MedicalRecord medicalRecord = medicalRecordDAO.findById(medicalRecordPage.getId());
        medicalRecord.setChief_complaint(medicalRecordPage.getChief_complaint());
        medicalRecord.setCurrent_treatment(medicalRecordPage.getCurrent_treatment());
        medicalRecord.setHistory_present_illness(medicalRecordPage.getHistory_present_illness());
        medicalRecord.setPast_history(medicalRecordPage.getPast_history());
        medicalRecord.setAllergic_history(medicalRecordPage.getAllergic_history());
        medicalRecord.setHealth_checkup(medicalRecordPage.getHealth_checkup());
        medicalRecord.setPrimary_diagnosis(medicalRecordPage.getPrimary_diagnosis());
        medicalRecord.setDiagnosis_type(medicalRecordPage.getDiagnosis_type());
        medicalRecord.setAnnouncements(medicalRecordPage.getAnnouncements());
        medicalRecord.setMedical_record_status(medicalRecordPage.getMedical_record_status());
        medicalRecordDAO.updateMedicalRecord(medicalRecord);
        //更新挂号信息中的看诊状态
        RegisterInfo registerInfo = registerInfoDAO.findById(medicalRecordPage.getRegister_id());
        registerInfo.setStatus(medicalRecordPage.getStatus());
        registerInfoDAO.updateRegisterInfo(registerInfo);
    }


    /**
     * @Description 根据疾病名称模糊查询疾病
     * @Param [name]
     * @Return java.util.List<edu.neu.entity.Disease>
     */
    @Override
    public List<DiseaseResult> getDiseaseDetailByName(String name) {
        return diseaseDAO.findByName(name);
    }


    /**
     * @Description 添加处方
     * @Param [prescription]
     * @Return void
     */
    @Override
    public Prescription addPrescription(Prescription prescription) {
        prescriptionDAO.insertPrescription(prescription);
        return prescriptionDAO.findBySelf(prescription);
    }

    /**
     * @Description 获取处方
     * @Param [medicalNum, registerId, doctor, type]
     * @Return java.util.List<edu.neu.entity.Prescription>
     */
    @Override
    public List<Prescription> getPrescriptionByMedicalInfo(int medicalNum, int registerId, int doctor, int type) {
        return prescriptionDAO.findByMedicalInfo(medicalNum, registerId, doctor, type);
    }

    /**
     * @Description 更新处方名称
     * @Param [id, name]
     * @Return void
     */
    @Override
    public void updatePrescriptionName(int id, String name) {
        prescriptionDAO.updatePrescription(id, name);
    }

    /**
     * @Description 删除处方
     * @Param [id]
     * @Return void
     */
    @Override
    public void deletePrescription(int id) {
        prescriptionDAO.deletePrescription(id);
        //同时需要删除处方明细的内容
        prescriptionItemDAO.deletePrescription(id);
    }

    /**
     * @Description 作废处方
     * @Param [id]
     * @Return void
     */
    @Override
    public void cancelPrescription(int id) {
        prescriptionDAO.cancelPrescription(id);
    }

    /**
     * @Description 暂存或开立处方增加处方明细
     * @Param [id]
     * @Return void
     */
    @Override
    public void storePrescription(List<PrescriptionItem> list, int flag) {
        if (flag == 2) {
            prescriptionDAO.estbPrescription(list.get(0).getPrescription_id());
        }
        //先删除之前的 在添加新的
        for (PrescriptionItem prescriptionItem : list) {
            prescriptionItemDAO.deletePrescription(prescriptionItem.getPrescription_id());
        }
        for (PrescriptionItem prescriptionItem : list
        ) {
            prescriptionItemDAO.addPrescriptionItem(prescriptionItem);
        }
    }

    /**
     * @Description 根据处方id查询处方明细
     * @Param [id]
     * @Return java.util.List<edu.neu.entity.PrescriptionItem>
     */
    @Override
    public List<PrescriptionItemPage> getByPrescriptionId(int id) {
        return prescriptionItemDAO.findPageByPrescriptionId(id);
    }

    /**
     * @Description 删除处方明细
     * @Param [id]
     * @Return void
     */
    @Override
    public void deletePrescriptionItem(int id) {
        prescriptionItemDAO.deletePrescriptionItem(id);
    }

    /**
     * @Description 模糊搜索药品
     * @Param [name]
     * @Return java.util.List<edu.neu.entity.Drug>
     */
    @Override
    public List<Drug> getDrugByName(String name) {
        return drugDAO.findByName(name);
    }


    /**
     * @Description 获取结算页信息
     * @Param [medicalNum]
     * @Return java.util.List<edu.neu.entity.CostPage>
     */
    @Override
    public List<CostPage> getCostPage(int medicalNum) {
        List<CostPage> list = prescriptionItemDAO.findCostPageByMedicalNum(medicalNum);
        for (CostPage costPage : list
        ) {
            if (costPage.getStatus().equals("1")) {
                costPage.setStatus("暂存");
            } else if (costPage.getStatus().equals("2")) {
                costPage.setStatus("开立");
            } else {
                costPage.setStatus("作废");
            }
        }
        return list;
    }

    /**
     * @Description 缴费
     * @Param [invoice_id, charge_method, ids]
     * @Return void
     */
    @Override
    public void settle(int invoice_id, String charge_method, List<Integer> ids) {
        List<ChargesDetails> list = new ArrayList<>();
        double amount = 0;
        //获取当前操作用户
        User user = (User) Util.currentRequest().getSession().getAttribute("user");
        //处方明细状态
        for (int id : ids
        ) {
            //设置明细已缴费
            prescriptionItemDAO.setChargeStatus(id, 2);
            //获取处方明细
            PrescriptionItem prescriptionItem = prescriptionItemDAO.findById(id);
            //获取处方
            Prescription prescription = prescriptionDAO.findById(prescriptionItem.getPrescription_id());
            //获取药品
            Drug drug = drugDAO.findById(prescriptionItem.getDrug());
            //金额
            amount += drug.getDrugsPrice() * prescriptionItem.getQuantity();
            list.add(new ChargesDetails(prescription.getRegister_id(), prescriptionItem.getDrug(), 1, drug.getDrugsName(), drug.getDrugsPrice(), prescriptionItem.getQuantity(), new Date(), user.getId(), charge_method, invoice_id, 1));//前面1表示药品 后面的表示收退费类型
        }
        //添加缴费信息
        for (ChargesDetails chargesDetails : list
        ) {
            chargesDetailDAO.insertChargesDetail(chargesDetails);
        }
        //添加发票信息
        Invoice invoice = Invoice.getInstance();
        invoice.setInvoice_id(invoice_id);
        invoice.setUser(user.getId());
        invoice.setTime(new Date());
        invoice.setStatus(1);
        invoice.setAmount(amount);
        invoiceDAO.updateInvoice(invoice);
    }


}
