package com.hym.eshoplib.http.mz;

import com.hym.httplib.interfaces.IHttpResultListener;

import app.App;
import cn.hym.superlib.utils.http.HttpUtil;
import cn.hym.superlib.utils.user.UserUtil;

import static cn.hym.superlib.utils.http.ApiExcuter.post;

public class MzNewApi {

    // 本地测试服;
    private static String host_ip = "http://121.40.80.45/api";


    /**
     * 产品 以及分类 ;
     *
     * @param listener
     * @param clazz
     * @param <T>
     */
    public static <T> void getOneType(/*String channelId,*/ IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("GetoneType");
//        request.AddParems("token", UserUtil.getToken(App.instance));
//        request.addParamsNotEmpty("channelid", channelId);
        post(request, listener, clazz);
    }


    /**
     * 产品 二级分类 ;
     *
     * @param listener
     * @param clazz
     * @param <T>
     */
    public static <T> void getTwoType(IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("GettwoType");
//        request.AddParems("token", UserUtil.getToken(App.instance));
//        request.addParamsNotEmpty("channelid", channelId);
        post(request, listener, clazz);
    }


    /**
     * icon 关联列表
     *
     * @param iconId
     * @param listener
     * @param clazz
     * @param <T>
     */
    public static <T> void getProductList(String iconId,
                                          String p,
                                          IHttpResultListener<T> listener, Class<T> clazz) {

        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("GetProductionList");
        request.addParamsNotEmpty("token", UserUtil.getToken(App.instance));
        request.addParamsNotEmpty("p", p);
        request.addParamsNotEmpty("psize", "20");
        request.addParamsNotEmpty("icon_id", iconId);

        post(request, listener, clazz);
    }


}
