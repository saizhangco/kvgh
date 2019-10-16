package saizhang.erp.kvgh.utils;

/**
 * @Author : saizhang
 * @Date : 2019/10/06
 * @Time : 18:55
 * @Description : TODO
 */
public class CountUtil {
    public static String getCount(Long count) {
        StringBuilder stringBuilder = new StringBuilder();
        int temp = (int) (count % 1000);
        stringBuilder.append(temp / 100);
        stringBuilder.append(temp % 100 / 10);
        stringBuilder.append(temp % 10);
        return stringBuilder.toString();
    }
}
