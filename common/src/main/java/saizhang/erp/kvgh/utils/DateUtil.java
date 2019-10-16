package saizhang.erp.kvgh.utils;

import java.util.Calendar;

/**
 * @Author : saizhang
 * @Date : 2019/10/06
 * @Time : 18:47
 * @Description : TODO
 */
public class DateUtil {
    public static String getDateString() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(year - 1911);
        stringBuilder.append( month > 9 ? month : "0" + month);
        stringBuilder.append( day > 9 ? day : "0" + day);
        return stringBuilder.toString();
    }
}
