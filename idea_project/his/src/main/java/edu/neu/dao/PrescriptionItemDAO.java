package edu.neu.dao;

        import edu.neu.entity.CostPage;
        import edu.neu.entity.PrescriptionItem;
        import edu.neu.entity.PrescriptionItemPage;

        import java.util.List;

/**
 * ClassName:PrescriptionItemDAO
 * Package:edu.neu.dao
 * Description:
 *
 * @Date:2019/9/6 11:51
 * @Author:HetFrame
 */
public interface PrescriptionItemDAO {
    PrescriptionItem findById(int id);
    List<PrescriptionItem> findByPrescriptionId(int id);
    void addPrescriptionItem(PrescriptionItem prescriptionItem);
    void deletePrescriptionItem(int id);
    void deletePrescription(int id);
    List<PrescriptionItemPage> findPageByPrescriptionId(int id);
    void updatePrescriptionItem(PrescriptionItem prescriptionItem);
    List<CostPage> findCostPageByMedicalNum(int medicalNum);
    void setChargeStatus(int id,int status);
}
