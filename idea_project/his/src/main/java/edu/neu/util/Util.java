package edu.neu.util;

import edu.neu.entity.Invoice;
import edu.neu.entity.MedicalRecord;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:Util
 * Package:edu.neu.util
 * Description: 一些工具方法
 *
 * @Date:2019/9/13 11:28
 * @Author:HetFrame
 */
public class Util {

    private Util(){

    }

    private static Map<String, ArrayDeque> map;

    /**
     * @Description 用于存放生成的多余的发票号和病历号
     * @Param []
     * @Return java.util.Map<java.lang.String, java.util.Queue>
     */
    public static Map<String, ArrayDeque> getMap() {
        if (map == null) {
            map = new HashMap<>();
            map.put("invoiceId", new ArrayDeque<Invoice>());
            map.put("medicalNum", new ArrayDeque<MedicalRecord>());
        }
        return map;
    }

    /**
     * @Description 获取当前request
     * @Param
     * @Return
     */

    public static HttpServletRequest currentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * @Description 获取当前日期时间的字符串
     * @Param []
     * @Return java.lang.String
     */
    public static String getDateTimeString() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
    /**
     * @Description 获取Date类型的日期时间
     * @Param []
     * @Return java.util.Date
     */
    public static Date getDateTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String dateStr = simpleDateFormat.format(date);
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     *@Description 获取当天日期String
     *@Param []
     *@Return java.lang.String
     */
    public static String getDateString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
}
