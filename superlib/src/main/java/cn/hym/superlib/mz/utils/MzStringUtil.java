package cn.hym.superlib.mz.utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.List;

public class MzStringUtil {


    /**
     * 字符串 用 ， 分开
     *
     * @param list
     * @return
     */
    public static String v(List<String> list) {
        StringBuffer sb = new StringBuffer();
        if (list.size() <= 1) {
            sb.append(list.get(0));
        } else {

            for (int i = 0; i < list.size() - 1; i++) {
                sb.append(list.get(i))
                        .append(",");
            }
            sb.append(list.get(list.size() - 1));

        }

        return sb.toString();
    }


    /**
     * double 距离 转  公里；
     *
     * @param dis
     * @return
     */
    public static String distance(double dis) {
        double result = dis / 1000;
        result = (double) Math.round(result * 100) / 100;
        return result + "公里";
    }


    /**
     * 分割 带 , 的字符串 ;
     *
     * @param tags
     * @return
     */
    public static String[] splitTag(String tags) {

        if (TextUtils.isEmpty(tags)) {
            return null;
        }

        boolean contains = tags.contains(",");
        if (contains) {
            String[] newTags = tags.split(",");
            return newTags;
        } else {
            return new String[]{tags};
        }
    }


    /**
     * 隐藏用户名 用 * 代替 ;
     *
     * @param userName
     * @return
     */
    public static String hideUserName(String userName) {


        String newName = userName.charAt(0) + "***" + userName.charAt(userName.length() - 1);

        return newName;
    }


}
