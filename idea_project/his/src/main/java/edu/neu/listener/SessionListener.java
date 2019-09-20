package edu.neu.listener;

import edu.neu.entity.Invoice;
import edu.neu.entity.MedicalRecord;
import edu.neu.util.Util;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * ClassName:SessionListener
 * Package:edu.neu.listener
 * Description:
 *
 * @Date:2019/8/31 16:42
 * @Author:HetFrame
 */
public class SessionListener implements HttpSessionListener {
    /**
     * @Description 监听session的销毁, 将未使用的号码放入map
     * @Param [httpSessionEvent]
     * @Return void
     */
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        Invoice invoice = (Invoice) session.getAttribute("invoiceId");
        MedicalRecord medicalRecord = (MedicalRecord) session.getAttribute("medicalNum");
        if (invoice != null) {
            Util.getMap().get("invoiceId").add(invoice);
        }
        if (medicalRecord != null) {
            Util.getMap().get("medicalNum").add(medicalRecord);
        }
    }
}