package com.hym.eshoplib.util;

/**
 * Created by 胡彦明 on 2018/8/29.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class CheckUtils {
    public static boolean isLegalId(String id){
        if (id.toUpperCase().matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")){
            return true;
        }else {
            return false;
        }
    }
}
