package cn.hym.superlib.utils.common;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Vector;

import cn.hym.superlib.R;

/**
 * create by 陈晖 2017/12/27
 * Java时间处理
 */
public class LocalTimeUtils {

    public static final String FORMAT_0 = "yyyy-MM-dd";

    public static final String FORMAT_1 = "HH:mm:ss";

    public static final String FORMAT_2 = "yyyy-MM-dd'T'HH:mm:ss.SSS";


    /**
     * 不能实例化
     */
    private LocalTimeUtils() {
    }

    /**
     * 得到日期的字符串
     *
     * @param date   日期对象
     * @param format 格式化类型
     * @return
     */
    public static String getDateStr(Date date, String format) {
        SimpleDateFormat simpleDateFormat;
        return (simpleDateFormat = new SimpleDateFormat(format)).format(date);
    }

    /**
     * 得到日期的字符串
     *
     * @param longDate 时间戳
     * @param format   格式化类型
     * @return
     */
    public static String getDateStr(long longDate, String format) {
        SimpleDateFormat simpleDateFormat;
        return (simpleDateFormat = new SimpleDateFormat(format)).format(longDate);
    }

    /**
     * 获取计算机当前时间
     *
     * @param format 格式化类型，例如"yyyy-MM-dd"
     * @return
     */
    public static String getNowString(String format) {
        return getDateStr(System.currentTimeMillis(), format);
    }

    /**
     * 获取当前日期对应的星期数.
     * <br>1=星期天,2=星期一,3=星期二,4=星期三,5=星期四,6=星期五,7=星期六
     *
     * @return 当前日期对应的星期数
     */
    public static int dayOfWeek() {
        GregorianCalendar g = new GregorianCalendar();
        int ret = g.get(Calendar.DAY_OF_WEEK);
        g = null;
        return ret;
    }

    /**
     * 获取时间添加偏移量后的时间(天)
     *
     * @param date   参考时间
     * @param offset 偏移量(天)，大于0为未来 小于0为过去
     * @return
     */
    public static Date getOffsetDay(Date date, int offset) {
        return calculate(date, GregorianCalendar.DATE, offset);
    }

    /**
     * 获取时间添加偏移量后的时间(分钟)
     *
     * @param date   参考时间
     * @param offset 偏移量(分钟)，大于0为未来 小于0为过去
     * @return
     */
    public static Date getOffsetMinute(Date date, int offset) {
        return calculate(date, GregorianCalendar.MINUTE, offset);
    }

    /**
     * 获取时间添加偏移量后的时间(小时)
     *
     * @param date   参考时间
     * @param offset 偏移量(小时)，大于0为未来 小于0为过去
     * @return
     */
    public static Date getOffsetHour(Date date, int offset) {
        return calculate(date, GregorianCalendar.HOUR, offset);
    }

    /**
     * 获取时间添加偏移量后的时间(年)
     *
     * @param date   参考时间
     * @param offset 偏移量(年)，大于0为未来 小于0为过去
     * @return
     */
    public static Date getoffsetYear(Date date, int offset) {
        return calculate(date, GregorianCalendar.YEAR, offset);
    }

    /**
     * 对日期(时间)中由field参数指定的日期成员进行加减计算. <br>
     * 例子: <br>
     * 如果Date类型的d为 2005年8月20日,那么 <br>
     * calculate(d,GregorianCalendar.YEAR,-10)的值为1995年8月20日 <br>
     * 而calculate(d,GregorianCalendar.YEAR,+10)的值为2015年8月20日 <br>
     *
     * @param d      日期(时间).
     * @param field  日期成员. <br>
     *               日期成员主要有: <br>
     *               年:GregorianCalendar.YEAR <br>
     *               月:GregorianCalendar.MONTH <br>
     *               日:GregorianCalendar.DATE <br>
     *               时:GregorianCalendar.HOUR <br>
     *               分:GregorianCalendar.MINUTE <br>
     *               秒:GregorianCalendar.SECOND <br>
     *               毫秒:GregorianCalendar.MILLISECOND <br>
     * @param amount 加减计算的幅度.+n=加n个由参数field指定的日期成员值;-n=减n个由参数field代表的日期成员值.
     * @return 计算后的日期(时间).
     */
    private static Date calculate(Date d, int field, int amount) {
        if (d == null)
            return null;
        GregorianCalendar g = new GregorianCalendar();
        g.setGregorianChange(d);
        g.add(field, amount);
        return g.getTime();
    }

    /**
     * 得到日期字符串对应的日期
     *
     * @param timeString 源格式化时间字符串
     * @param format     时间格式
     * @param l          出错返回默认类型
     * @return
     */
    public static Date getDate(String timeString, String format, long l) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date;
        try {
            date = simpleDateFormat.parse(timeString);
        } catch (ParseException _ex) {
            date = new Date(l);
        }
        return date;
    }

    /**
     * 得到日期字符串对应的日期
     *
     * @param timeString 源格式化时间字符串
     * @param format     时间格式
     * @return
     */
    public static Date getDate(String timeString, String format) {
        return getDate(timeString, format, 0L);
    }

    /**
     * 获取当前日期的时间戳
     *
     * @return
     */
    public static long getNowTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 得到日期字符串对应的时间戳
     *
     * @param timeString 源时间字符串
     * @param format     时间格式
     * @param l          出错返回默认类型
     * @return 时间戳
     */
    public static long getTimestamp(String timeString, String format, long l) {
        return getDate(timeString, format, l).getTime();
    }

    /**
     * 得到日期字符串对应的时间戳
     *
     * @param timeString 源时间字符串
     * @param format     时间格式
     * @return 时间戳
     */
    public static long getTimestamp(String timeString, String format) {
        return getTimestamp(timeString, format, 0L);
    }

    /**
     * 转换日期字符串的格式
     *
     * @param timeString     源日期字符串
     * @param originalFormat 格式化类型1
     * @param targetFormat   格式化类型2
     * @return
     */
    public static String convertDateFormat(String timeString, String originalFormat, String targetFormat) {
        Date date = getDate(timeString, originalFormat);
        if (null == date) {
            return "";
        } else {
            return getDateStr(date, targetFormat);
        }
    }


    public static String convertTimeZone(String originFormater,
                                         String originTimeString, String targetFormater, String targetTimeZoneId) {
        if (originFormater == null || "".equals(originFormater))
            return null;
        if (originTimeString == null || "".equals(originTimeString))
            return null;
        if (targetFormater == null || "".equals(targetFormater))
            return null;
        if (targetTimeZoneId == null || "".equals(targetTimeZoneId))
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(originFormater);
        try {
            int diffTime = getDiffTimeZoneRawOffset(targetTimeZoneId);
            Date d = sdf.parse(originTimeString);
            long nowTime = d.getTime();
            long newNowTime = nowTime - diffTime;
            d = new Date(newNowTime);
            return getDateStr(d, targetFormater);
        } catch (ParseException e) {
            return null;
        } finally {
            sdf = null;
        }
    }

    /**
     * 获取系统当前默认时区与指定时区的时间差.(单位:毫秒)
     *
     * @param timeZoneId 时区Id
     * @return 系统当前默认时区与指定时区的时间差.(单位:毫秒)
     */
    private static int getDiffTimeZoneRawOffset(String timeZoneId) {
        return TimeZone.getDefault().getRawOffset()
                - TimeZone.getTimeZone(timeZoneId).getRawOffset();
    }

    /**
     * 获取系统当前默认时区与UTC的时间差.(单位:毫秒)
     *
     * @return 系统当前默认时区与UTC的时间差.(单位:毫秒)
     */
    private static int getDefaultTimeZoneRawOffset() {
        return TimeZone.getDefault().getRawOffset();
    }

    /**
     * 获取所有的时区编号. <br>
     * 排序规则:按照ASCII字符的正序进行排序. <br>
     * 排序时候忽略字符大小写.
     *
     * @return 所有的时区编号(时区编号已经按照字符[忽略大小写]排序).
     */
    public static String[] fecthAllTimeZoneIds() {
        Vector v = new Vector();
        String[] ids = TimeZone.getAvailableIDs();
        for (int i = 0; i < ids.length; i++) {
            v.add(ids[i]);
        }
        Collections.sort(v, String.CASE_INSENSITIVE_ORDER);
        v.copyInto(ids);
        v = null;
        return ids;
    }

    public static String getInterval(Context context,Date createAt) {
        // 定义最终返回的结果字符串。
        String interval = null;

        long millisecond = new Date().getTime() - createAt.getTime();

        long second = millisecond / 1000;

        if (second <= 0) {
            second = 0;
        }
        //*--------------微博体（标准）
        if (second == 0) {
            //刚刚
            interval = context.getResources().getString(R.string.justnow);
        } else if (second < 30) {
            //秒以前
            interval = second + context.getResources().getString(R.string.secondsago);
        } else if (second >= 30 && second < 60) {
            //半分前
            interval = context.getResources().getString(R.string.halfminutesago);
        } else if (second >= 60 && second < 60 * 60) {//大于1分钟 小于1小时
            //多少分前
            long minute = second / 60;
            interval = minute +" "+ context.getResources().getString(R.string.minutesago);
        } else if (second >= 60 * 60 && second < 60 * 60 * 24) {
            //大于1小时 小于24小时 小时前
            long hour = (second / 60) / 60;
//            if (hour <= 3) {
            interval = hour +" "+ context.getResources().getString(R.string.hoursago);
//            } else {
//                interval = "今天" + getFormatTime(createAt, "HH:mm");
//            }
        }
        else if (second >= 60 * 60 * 24 && second <= 60 * 60 * 24 * 2) {//大于1D 小于2D
            //昨天
            interval = context.getResources().getString(R.string.yestday)+ getFormatTime(createAt, "HH:mm");
        }
        else if (second > 60 * 60 * 24 && second < 60 * 60 * 24 * 7) {//大于24小时 小于 7天
            long day = ((second / 60) / 60) / 24;
            //天前
            interval = day +" "+ context.getResources().getString(R.string.daysago);
        }else if(second >= 60 * 60 * 24 * 7&&second<60 * 60 * 24 * 8){
            //大于等于7天 <8天 一周前
            interval="1 "+ context.getResources().getString(R.string.weeksago);
        }else if(second>=60*60*24*8&&second<60*60*24*14){
            //在8天以上包括8天 到14天以内 正常显示xx天
            long day = ((second / 60) / 60) / 24;
            interval = day+" " + context.getResources().getString(R.string.daysago);
        }else if(second>=60*60*24*14&&second<60*60*24*15){
            //大于14天并且小于15天 显示2周前
            interval="2 "+context.getResources().getString(R.string.weeksago);
        }else if(second>=60*60*24*15&&second<60*60*24*21){
            //在15天以上包括15天 到21天以内 正常显示xx天
            long day = ((second / 60) / 60) / 24;
            interval = day+" " + context.getResources().getString(R.string.daysago);
        }else if(second>=60*60*24*21&&second<60*60*24*22){
            //大于21天并且小于2天 显示2周前
            interval="3 "+context.getResources().getString(R.string.weeksago);
        }else if(second>=60*60*24*22&&second<60*60*24*28){
            //在22天以上包括22天 到28天以内 正常显示xx天
            long day = ((second / 60) / 60) / 24;
            interval = day +" "+ day + context.getResources().getString(R.string.daysago);
        }else if(second>=60*60*24*28&&second<60 * 60 * 24 * 31){
            //大于28天并且小于29天 显示1周前
            interval=context.getResources().getString(R.string.lastmonth);
        }else if(second>=60*60*24*31&&second<60 * 60 * 24 * 62){
            interval="2 "+context.getResources().getString(R.string.monthsago);
        }
        else if(second>=60*60*24*62&&second<60 * 60 * 24 * 93){
            interval="3 "+context.getResources().getString(R.string.monthsago);
        }
        else if(second>=60*60*24*93&&second<60 * 60 * 24 * 124){
            interval="4 "+context.getResources().getString(R.string.monthsago);
        }
        else if(second>=60*60*24*124&&second<60 * 60 * 24 * 155){
            interval="5 "+context.getResources().getString(R.string.monthsago);
        }
        else if(second>=60*60*24*155&&second<60 * 60 * 24 * 186){
            //半年以前
            interval=context.getResources().getString(R.string.halfyear);
        }
        else if (second <= 60 * 60 * 24 * 365 && second >= 60 * 60 * 24 * 186) {//大于半年小于365天
            interval = getFormatTime(createAt, "MM-dd HH:mm");
        } else if (second >= 60 * 60 * 24 * 365) {//大于365天
            interval = getFormatTime(createAt, "yyyy-MM-dd HH:mm");
        } else {
            interval = "0";
        }
        return interval;
    }

    public static String getFormatTime(Date date, String Sdf) {
        return (new SimpleDateFormat(Sdf)).format(date);
    }
}
