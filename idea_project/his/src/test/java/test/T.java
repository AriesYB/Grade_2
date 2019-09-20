package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ClassName:T
 * Package:test
 * Description:
 *
 * @Date:2019/8/31 23:16
 * @Author:HetFrame
 */
public class T {
    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String dateStr = simpleDateFormat.format(date);
        date = simpleDateFormat.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,1);
        System.out.println(date);
        System.out.println(calendar.getTime());
    }
}
