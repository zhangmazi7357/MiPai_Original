package cn.hym.superlib.utils.common;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具
 **/
public class DateUtil {


    /**
     *  时间戳 转换成固定格式字符串
     */
    public static String getTimeStamp(String time,String format) {
        if(TextUtils.isEmpty(format)){
            format="yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        long timeLong = Long.parseLong(time);
        return sdf.format(new Date(timeLong * 1000L));

    }
    /*
 * 毫秒转化时分秒毫秒
 */
    public static String formatTime(Long ms,int type) {
        long result=0;
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if(day > 0) {
            sb.append(day+"天");
        }
        if(hour > 0) {
            sb.append(hour+"小时");
        }
        if(minute > 0) {
            sb.append(minute+"分");
        }
        if(second > 0) {
            sb.append(second+"秒");
        }
//        if(milliSecond > 0) {
//            sb.append(milliSecond+"毫秒");
//        }
       switch (type){
           case 0:
               result=day;//天
               break;
           case 1:
               result=hour;//
               break;
           case 2:
               result=minute;
               break;
           case 3:
               result=second;
               break;
           case 4:
               result=milliSecond;
               break;
       }
//        Logger.d("时间="+sb.toString());
       return sb.toString();
    }
    public static String getTime() {
        SimpleDateFormat sf = null;
        Date d = new Date(System.currentTimeMillis());
        sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);

    }
    public static Calendar StringToCalendar(String time) {
        String str = time;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }




}
