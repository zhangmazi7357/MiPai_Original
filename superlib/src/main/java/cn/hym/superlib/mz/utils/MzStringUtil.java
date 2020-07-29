package cn.hym.superlib.mz.utils;

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
     * 分割 tag
     *
     * @param tags
     * @return
     */
    public static String[] splitTag(String tags) {

        boolean contains = tags.contains(",");
        if (contains) {
            String[] newTags = tags.split(",");
            return newTags;
        } else {
            return new String[]{tags};
        }
    }
}
