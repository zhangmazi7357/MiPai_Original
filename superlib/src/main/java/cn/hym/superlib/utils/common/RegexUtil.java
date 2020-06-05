package cn.hym.superlib.utils.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则验证工具类
 *
 */
public class RegexUtil {

    /**
     * 身份证号码
     */
    public static final String REGEX_ID = "(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}(\\d|x|X)$)";
    /**
     * 手机号
     */
    public static final String REGEX_MOBILE_PHONE = "^[1][34578][0-9]{9}$";
    /**
     * 电话号码
     */
    public static final String REGEX_PHONE = "^(([1][34578]\\d{9})|(((|(0\\d{2,3} )|(0\\d{2,3}-)|(0\\d{2,3}))(\\d{7,8}))(|-(\\d{4}|\\d{3}|\\d{2}|\\d{1})))$)";
    /**
     * 用户名(中英文，20位)
     */
    public static final String REGEX_NICKNAME = "[\u4e00-\u9fa5a-zA-Z0-9]{1,20}";
    /**
     * 合法的用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z\\d]*[a-zA-Z]+[a-zA-Z\\d]*$";
    /**
     * 邮箱
     */
    public static final String REGEX_EMAIL = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";

    /**
     * 身份证号码格式是否正确
     * @param str
     * @return
     */
    public static boolean isIdCode(CharSequence str){
        return check(str, REGEX_ID);
    }

    public static boolean isMobilePhoneNum(CharSequence str){
        return check(str, REGEX_MOBILE_PHONE);
    }

    public static boolean isNickname(CharSequence str){
        return check(str, REGEX_NICKNAME);
    }

    public static boolean isEmail(CharSequence str){
        return check(str, REGEX_EMAIL);
    }

    public static boolean isPassword(String phone) {
        if(phone == null){
            return false;
        }
        if(phone.length() >= 6 && phone.length() <= 20) {
            return true;
        }
        return false;
    }

    /**
     * 验证电话号码(包括座机)
     * @param phone
     * @return
     */
    public static boolean isPhoneNumber(String phone) {
        boolean b=false;
        if(check(phone,REGEX_PHONE)){
            return true;
        }
        return b;
    }
    //是手机或者座机
    public static boolean isPhoneOrMobile(String phone){
        boolean b=false;
        if(check(phone,REGEX_PHONE)){
            return true;
        }else{
            b= check(phone,REGEX_MOBILE_PHONE);
        }
        return b;
    }
    //将手机号中间4位变成*
    public static String formatPhoneNumber(String args) {
        Matcher matcher = Pattern.compile(REGEX_MOBILE_PHONE).matcher(args);
        if (matcher.find()) {
            return matcher.group().replaceAll("(?<=[\\d]{3})\\d(?=[\\d]{4})", "*");
        }
        return args;
    }
    public static boolean check(CharSequence str, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
