package cn.hym.superlib.mz.utils;

import java.util.List;

public class MzStringUtil {


    public static String v(List<String> list) {
        StringBuffer sb = new StringBuffer();
        if (list.size() <= 1) {
            sb.append(list.get(0));
        } else {

            for (int i = 0; i < list.size() - 1; i++) {
                sb.append(list.get(i))
                        .append(",");
            }
            sb.append(list.get(list.size() -1));

        }

        return sb.toString();
    }
}
