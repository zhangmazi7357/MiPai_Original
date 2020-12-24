package cn.hym.superlib.mz.utils;

import android.text.TextUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static String[] splitComma(String tags) {

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
     * 分割 date 时间 ;
     *
     * @param time
     * @return
     */
    public static String splitTime(String time) {
        if (TextUtils.isEmpty(time)) {
            return null;
        }

        boolean contains = time.contains(" ");
        if (contains) {
            String[] s = time.split(" ");
            return s[0];
        } else {
            return time;
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


    /**
     * 判断 字符串中是否包含数字 ;
     *
     * @param content
     * @return
     */
    public  static boolean HasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

}
