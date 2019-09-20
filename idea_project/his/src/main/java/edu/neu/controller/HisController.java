package edu.neu.controller;

import edu.neu.entity.*;
import edu.neu.service.HisService;
import edu.neu.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * ClassName:HisController
 * Package:edu.neu.handlers
 * Description:
 *
 * @Date:2019/8/15 16:44
 * @Author:HetFrame
 */

@Controller
public class HisController {

    @Autowired
    @Qualifier("hisService")
    private HisService hisService;

    /**
     * @Description 跳转至登录页面
     * @Param [request, response]
     * @Return void
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {//已登录
            return "redirect:/index";
        }
        return "login";//不能直接forword:login 会一直调用自己
    }


    /**
     * @Description 验证用户名密码
     * @Param [username, password]
     * @Return java.lang.String
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String check(String username, String password, Model model) {
        int flag = hisService.checkAccountPassword(username, password);
        if (flag == -1) {//用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "forward:login";
        } else if (flag == 0) {//密码错误
            model.addAttribute("msg", "密码错误");
            return "forward:login";
        }
        return "redirect:/index";
    }

    /**
     * @Description 主页面
     * @Param [request, response]
     * @Return void
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return "index";
    }

    /**
     * @Description 用户注销
     * @Param []
     * @Return java.lang.String
     */
    @RequestMapping("/logout")
    public String logout() {
        HttpSession session = Util.currentRequest().getSession(false);
        if (session != null) {
            session.removeAttribute("user");
            session.invalidate();//销毁session对象
        }
        return "redirect:/login";
    }


    /**
     * @Description 挂号
     * @Param [registerPage]
     * @Return java.lang.String
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String handleRegister(RegisterPage registerPage) {
        hisService.register(registerPage);
        //挂号成功后移除
        Util.currentRequest().getSession(false).removeAttribute("invoiceId");
        Util.currentRequest().getSession(false).removeAttribute("medicalNum");
        return "200";
    }

    /**
     * @Description 获取所有科室
     * @Param []
     * @Return java.util.List<edu.neu.entity.Department>
     */
    @RequestMapping("/registrar/department")
    @ResponseBody
    public List<Department> getDepartment() {
        return hisService.getAllDepartment();
    }

    /**
     * @Description 根据科室以及挂号级别获取医生
     * @Param [map]
     * @Return java.util.List<User>
     */
    @RequestMapping("/registrar/doctor")
    @ResponseBody
    public List<User> getDoctor(@RequestBody Map<String, Integer> map) {
        List<User> list = hisService.getUserByDepartment(map.get("department"));
        List<User> list1 = new ArrayList<>();
        for (User user : list
        ) {
            if (user.getRegister_class_id() == map.get("register_class")) {
                list1.add(user);
            }
        }
        return list1;
    }

    /**
     * @Description 获取一个新的发票号码
     * @Param []
     * @Return int
     */
    @RequestMapping("/registrar/invoice")
    @ResponseBody
    public int getInvoice() {
        return hisService.getInvoiceId();
    }

    /**
     * @Description 获取一个新的病历号
     * @Param []
     * @Return int
     */
    @RequestMapping("/registrar/medical")
    @ResponseBody
    public int getMedicalNum() {
        return hisService.getMedicalNum();
    }

    /**
     * @Description 根据病历号获取病人信息
     * @Param [map]
     * @Return Patient
     */
    @RequestMapping("/registrar/findpatient")
    @ResponseBody
    public Patient getPatientInfo(@RequestBody Map<String, Integer> map) {
        Patient patient = hisService.getPatient(map.get("medical_record_num"));
        return patient;
    }

    /**
     * @Description 获取挂号的费用
     * @Param [map]
     * @Return double
     */
    @RequestMapping(value = "/registrar/registerfee")
    @ResponseBody
    public double getRegisterFee(@RequestBody Map<String, Integer> map) {
        return hisService.getRegisterFee(map.get("register_class"));
    }

    /**
     * @Description 根据医生日期获取病历信息
     * @Param []
     * @Return java.util.Map<java.lang.String, java.util.List < edu.neu.entity.MedicalRecordPage>>
     */
    @RequestMapping("/doctor/medicalrecord")
    @ResponseBody
    public Map<String, List<MedicalRecordPage>> getMedicalRecordInfo() {
        //获取当前医生用户名
        User user = (User) Util.currentRequest().getSession().getAttribute("user");
        String username = user.getUsername();
        Map<String, List<MedicalRecordPage>> map = hisService.getMedicalByDoctor(username);
        return map;
    }

    @RequestMapping("/doctor/getNodes")
    @ResponseBody
    public List<Node> getNodes() {
        List<Node> list = new ArrayList<>();
        list.add(new Node(11, 1, "未诊患者", "false", "true", null));
        list.add(new Node(12, 1, "已诊患者", "false", "true", null));
        return list;
    }

    /**
     * @Description 获取病人信息的节点
     * @Param [id, pid, name]
     * @Return java.util.List<edu.neu.entity.Node>
     */
    @RequestMapping("/doctor/getPatientNodes")
    @ResponseBody
    public List<Node> getPatientNodes(int id, int pid, String name) {
        List<Node> list = new ArrayList<Node>();
        if (id == 11) {//加载未诊患者
            return hisService.getPatientByStatus(1);
        } else if (id == 12) {//加载已诊患者
            return hisService.getPatientByStatus(2);
        }
        return list;
    }

    /**
     * @Description 获取病历详情
     * @Param [id]
     * @Return edu.neu.entity.MedicalRecordPage
     */
    @RequestMapping("/doctor/getMedicalRecordDetail")
    @ResponseBody
    public MedicalRecordPage getMedicalRecordDetail(@RequestParam("id") int id) {
        return hisService.getMedicalRecordDetail(id);
    }

    /**
     * @Description 获取疾病详情
     * @Param [info]
     * @Return java.util.List<edu.neu.entity.DiseaseDetailPage>
     */
    @RequestMapping("/doctor/getDiseaseDetail")
    @ResponseBody
    public List<DiseaseDetailPage> getDiseaseDetail(@RequestParam("info") String info) {
        if (info.length() == 1) {
            return null;
        }
        String arr[] = info.split("_");
        List<String> list = new ArrayList<>();
        String ids[] = arr[0].split(",");
        String times[] = arr[1].split("=");
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == null || ids[i].equals("")) {
                continue;
            }
            list.add(ids[i]);
        }
        List<Disease> list1 = hisService.getDiseaseDetail(list);
        List<DiseaseDetailPage> result = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            result.add(new DiseaseDetailPage(list1.get(i), times[i]));
        }
        return result;
    }


    /**
     * @Description 根据疾病名称模糊搜索疾病
     * @Param [name]
     * @Return java.util.List<edu.neu.entity.Disease>
     */
    @RequestMapping("/doctor/getDiseaseDetailByName")
    @ResponseBody
    public List<DiseaseResult> getDiseaseDetailByName(@RequestParam("name") String name) {
        if (name == null || name.equals("")) {
            return null;
        }
        List<DiseaseResult> list = hisService.getDiseaseDetailByName(name);
        return list;
    }


    /**
     * @Description 医生看诊后更新数据 且挂号里面的认为已诊
     * @Param [medicalRecordPage]
     * @Return java.lang.String
     */
    @RequestMapping("/doctor/updateMedicalRecordDetail")
    @ResponseBody
    public String updateMedicalRecordDetail(@RequestBody MedicalRecordPage medicalRecordPage) {
        hisService.updateMedicalRecord(medicalRecordPage);
        return "200";
    }

    /**
     * @Description 添加处方
     * @Param [medical_record_num, register_id, type, name]
     * @Return java.lang.String
     */
    @RequestMapping("/doctor/addPrescription")
    @ResponseBody
    public String addPrescription(@RequestParam("medical_record_num") int medical_record_num, @RequestParam("register_id") int register_id, @RequestParam("type") int type, @RequestParam("name") String name) {
        User user = (User) Util.currentRequest().getSession().getAttribute("user");
        Prescription prescription = new Prescription(name, medical_record_num, register_id, user.getId(), type, Util.getDateTimeString());
        return String.valueOf(hisService.addPrescription(prescription).getId());
    }


    /**
     * @Description 获取处方信息
     * @Param [medical_record_num, register_id, type]
     * @Return java.util.List<edu.neu.entity.Prescription>
     */
    @RequestMapping("/doctor/getPrescription")
    @ResponseBody
    public List<Prescription> getPrescription(@RequestParam("medical_record_num") int medical_record_num, @RequestParam("register_id") int register_id, @RequestParam("type") int type) {
        User user = (User) Util.currentRequest().getSession().getAttribute("user");
        return hisService.getPrescriptionByMedicalInfo(medical_record_num, register_id, user.getId(), type);
    }

    /**
     * @Description 修改处方名称
     * @Param [id, name]
     * @Return java.lang.String
     */
    @RequestMapping("/doctor/updatePrescription")
    @ResponseBody
    public String updatePrescription(@RequestParam("id") int id, @RequestParam("name") String name) {
        hisService.updatePrescriptionName(id, name);
        return "200";
    }

    /**
     * @Description 删除处方
     * @Param [id]
     * @Return java.lang.String
     */
    @RequestMapping("/doctor/deletePrescription")
    @ResponseBody
    public String deletePrescription(@RequestParam("id") int id) {
        hisService.deletePrescription(id);
        return "200";
    }

    /**
     * @Description 作废处方
     * @Param [id]
     * @Return java.lang.String
     */
    @RequestMapping("/doctor/cancelPrescription")
    @ResponseBody
    public String cancelPrescription(@RequestParam("id") int id) {
        hisService.cancelPrescription(id);
        return "200";
    }

    /**
     * @Description 开立处方
     * @Param [id]
     * @Return java.lang.String
     */
    @RequestMapping("/doctor/storePrescription2")
    @ResponseBody
    public String estbPrescription(@RequestBody List<PrescriptionItemPage> list) {
        if (list.size() == 0) {
            return "200";
        }
        List<PrescriptionItem> list1 = new ArrayList<>();
        for (PrescriptionItemPage prescriptionItemPage : list
        ) {
            PrescriptionItem prescriptionItem = new PrescriptionItem(prescriptionItemPage);
            list1.add(prescriptionItem);
        }
        hisService.storePrescription(list1, 2);
        return "200";
    }

    /**
     * @Description 暂存处方
     * @Param [id]
     * @Return java.lang.String
     */
    @RequestMapping("/doctor/storePrescription1")
    @ResponseBody
    public String storePrescription(@RequestBody List<PrescriptionItemPage> list) {
        if (list.size() == 0) {
            return "200";
        }
        List<PrescriptionItem> list1 = new ArrayList<>();
        for (PrescriptionItemPage prescriptionItemPage : list
        ) {
            PrescriptionItem prescriptionItem = new PrescriptionItem(prescriptionItemPage);
            list1.add(prescriptionItem);
        }
        hisService.storePrescription(list1, 1);
        return "200";
    }

    /**
     * @Description 获取处方明细
     * @Param [id]
     * @Return java.util.List<edu.neu.entity.PrescriptionItemPage>
     */
    @RequestMapping("/doctor/getPrescriptionItem")
    @ResponseBody
    public List<PrescriptionItemPage> getPrescriptionItem(@RequestParam("id") int id) {
        return hisService.getByPrescriptionId(id);
    }

    @RequestMapping("/doctor/getDrugByName")
    @ResponseBody
    public List<Drug> getDrugByName(@RequestParam("name") String name) {
        return hisService.getDrugByName(name);
    }


    /**
     * @Description 获取病人费用信息
     * @Param [medicalNum]
     * @Return java.util.List<edu.neu.entity.CostPage>
     */
    @RequestMapping("/registrar/getPatientFee")
    @ResponseBody
    public List<CostPage> getPatientFee(@RequestParam("medicalNum") int medicalNum) {
        return hisService.getCostPage(medicalNum);
    }

    /**
     * @Description 获取结算发票号
     * @Param []
     * @Return java.lang.Integer
     */
    @RequestMapping("/registrar/getInvoice")
    @ResponseBody
    public Integer getFeeInvoice() {
        int invoice_id = hisService.getInvoiceId();
        return invoice_id;
    }

    @RequestMapping("/registrar/settle")
    @ResponseBody
    public Integer settle(@RequestBody List<String> list) {
        //获取发票号
        int invoice_id = Integer.parseInt(list.get(list.size() - 1));
        list.remove(list.size() - 1);
        //获取收费方式
        String charge_method = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        List<Integer> list1 = new ArrayList<>();
        for (String s : list
        ) {
            list1.add(Integer.parseInt(s));
        }
        hisService.settle(invoice_id, charge_method, list1);
        return 200;
    }

}
