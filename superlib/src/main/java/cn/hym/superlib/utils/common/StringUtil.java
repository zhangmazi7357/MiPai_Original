package cn.hym.superlib.utils.common;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

/**
 * Created by 胡彦明 on 2018/3/7.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class StringUtil {
    public static Spanned getHtmlString(String left, String right) {
        String html = "<p style=\"text-align: left\"><font style=\"font-size: " + "14px\" color=\"#000000\">" + "<big>" + "<small>" + left + "</small>" + "</big>" + "</font><font style=\"font-size: 12px\" color=\"#a3a3a3\">" + "<small>" + right + "<small>" + "</font></p>";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }
    //获取回复信息的html格式
    public static Spanned getReplayHtmlString(String content,String replayUserNick,String replayContent){
        String html="<p style=\"text-align: left\">"
                     + "<font style=\"font-size: " + "14px\" color=\"#000000\">" + "<big>" + "<small>" + content +"//"+ "</small>" + "</big>" + "</font>"
                     + "<font style=\"font-size: 14px\" color=\"#e98563\">" + "<big>"+"<small>" +"@"+ replayUserNick +" : "+ "</small>"+ "</big>" + "</font>"
                     + "<font style=\"font-size: 14px\" color=\"#000000\">"+ "<big>" +"<small>"+replayContent +"</small>"+"</big>"+ "</font>"
                     + "</p>";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }
    //获取系统消息里的回复消息 格式
    //获取回复信息的html格式
    public static Spanned getSytemReplayHtmlString(String nick,String type){
        String html="<p style=\"text-align: left\">"
                + "<font style=\"font-size: 14px\" color=\"#e98563\">" + "<big>"+"<small>" +nick+ "</small>"+ "</big>" + "</font>"
                + "<font style=\"font-size: 14px\" color=\"#000000\">"+ "<big>" +"<small>"+type +"</small>"+"</big>"+ "</font>"
                + "</p>";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }

}
