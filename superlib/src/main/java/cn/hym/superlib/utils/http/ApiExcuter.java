package cn.hym.superlib.utils.http;

import android.content.Context;
import android.text.TextUtils;

import com.hym.httplib.HttpManager;
import com.hym.httplib.interfaces.IHttpResultListener;
import com.hym.httplib.model.HttpRequest;

import cn.hym.superlib.utils.common.SharePreferenceUtil;

/**
 * Created by 胡彦明 on 2017/7/4.
 * <p>
 * Description 请求执行者
 * <p>
 * otherTips
 */

public class ApiExcuter {
    public static Context context;

    public static void setContext(Context context) {
        ApiExcuter.context = context;
    }

    //执行post请求
    public static void post(HttpRequest request, IHttpResultListener listener, Class clazz) {
        String channelid = SharePreferenceUtil.getStringData(context, "channelid");
        if (context != null && !TextUtils.isEmpty(channelid)) {
            request.AddParems("channelid", channelid);
            request.AddParems("device_type", "1");
        }
        HttpManager.getInstance().post(request, listener, clazz);
    }

    //执行post请求
    public static void postFirstPager(HttpRequest request, String type, IHttpResultListener listener, Class clazz) {
        String channelid = SharePreferenceUtil.getStringData(context, "channelid");
        if (context != null && !TextUtils.isEmpty(channelid)) {
            request.AddParems("channelid", channelid);
            request.AddParems("device_type", type);

        }
        HttpManager.getInstance().post(request, listener, clazz);
    }

    //执行post请求
    public static void postTest(HttpRequest request, IHttpResultListener listener, Class clazz) {
        String channelid = SharePreferenceUtil.getStringData(context, "channelid");
        if (context != null && !TextUtils.isEmpty(channelid)) {
            request.AddParems("channelid", "191e35f7e022b74482d");
            request.AddParems("device_type", "1");
        }
        HttpManager.getInstance().post(request, listener, clazz);
    }
    //执行get请求

}
