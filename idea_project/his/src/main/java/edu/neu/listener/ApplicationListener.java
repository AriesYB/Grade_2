package edu.neu.listener;

import edu.neu.entity.Invoice;
import edu.neu.entity.MedicalRecord;
import edu.neu.service.HisService;
import edu.neu.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import java.util.ArrayDeque;

/**
 * ClassName:AppliactionListener
 * Package:edu.neu.listener
 * Description:
 *
 * @Date:2019/8/31 16:58
 * @Author:HetFrame
 */
public class ApplicationListener extends ContextLoaderListener {

    @Autowired
    @Qualifier("basicService")
    private HisService hisService;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
    }

    /**
     * @Description web应用停止时，将未使用的号码从数据库删除
     * @Param [event]
     * @Return void
     */

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        Util.currentRequest().getSession().invalidate();
        ArrayDeque<Invoice> arrayDeque1 = Util.getMap().get("invoiceId");
        ArrayDeque<MedicalRecord> arrayDeque2 = Util.getMap().get("medicalNum");
        for (Invoice invoice : arrayDeque1
        ) {
            hisService.deleteInvoiceId(invoice.getInvoice_id());
        }
        for (MedicalRecord medicalRecord : arrayDeque2
        ) {
            hisService.deleteMedicalNum(medicalRecord.getMedical_record_num());
        }
        super.contextDestroyed(event);
    }
}
