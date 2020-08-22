package com.hym.eshoplib.http.mz;

import android.provider.ContactsContract;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.hym.httplib.interfaces.IHttpResultListener;

import app.App;
import cn.hym.superlib.utils.http.HttpUtil;
import cn.hym.superlib.utils.user.UserUtil;

import static cn.hym.superlib.utils.http.ApiExcuter.post;

public class MzNewApi {

    private static String TAG = "MzNewApi";


    /**
     * 产品 以及分类 ;
     *
     * @param listener
     * @param clazz
     * @param <T>
     */
    public static <T> void getOneType(IHttpResultListener<T> listener, Class<T> clazz) {
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
                                          IHttpResultListener<T> listener,
                                          Class<T> clazz) {

        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("GetProductionList");
        request.addParamsNotEmpty("token", UserUtil.getToken(App.instance));
        request.addParamsNotEmpty("p", p);
        request.addParamsNotEmpty("psize", "20");
        request.addParamsNotEmpty("icon_id", iconId);

        post(request, listener, clazz);
    }


    /**
     * 发表评价
     *
     * @param case_id
     * @param content
     * @param score
     * @param tag_id
     * @param images
     * @param pid
     * @param listener
     * @param clazz
     * @param <T>
     */
    public static <T> void sendComment(String case_id,
                                       String log_id,
                                       String content, String score,
                                       String tag_id, String images,
                                       String pid,
                                       IHttpResultListener<T> listener,
                                       Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Comment");
        request.setClassName("Publish");
        request.addParamsNotEmpty("token", UserUtil.getToken(App.instance));
        request.addParamsNotEmpty("case_id", case_id);
        request.addParamsNotEmpty("log_id", log_id);
//        request.addParamsNotEmpty("title", title);
        request.addParamsNotEmpty("content", content);
        request.addParamsNotEmpty("score", score);
        request.addParamsNotEmpty("tag_id", tag_id);
        request.addParamsNotEmpty("images", images);
        request.addParamsNotEmpty("pid", pid);

        Log.e(TAG, "添加评论 参数: " + JSONObject.toJSONString(request));

        post(request, listener, clazz);

    }


    /**
     * 搜索 ；
     *
     * @param str
     * @param type
     * @param sort_type
     * @param p
     * @param listener
     * @param clazz
     * @param <T>
     */
    public static <T> void search(String str, String type,
                                  String sort_type,
                                  String region_id,
                                  String p,
                                  IHttpResultListener<T> listener,
                                  Class<T> clazz) {

        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("Search");
        request.addParamsNotEmpty("str", str);
        request.addParamsNotEmpty("type", type);
        request.addParamsNotEmpty("sort_type", sort_type);
        request.addParamsNotEmpty("token", UserUtil.getToken(App.instance));
        request.addParamsNotEmpty("region_id", region_id);
        request.addParamsNotEmpty("p", p);
        request.addParamsNotEmpty("psize", "10");

//        Log.e(TAG, "搜索 参数: " + JSONObject.toJSONString(request));
        post(request, listener, clazz);

    }


    // 商品详情 评论
    public static <T> void getComment(String case_id, String p,
                                      IHttpResultListener<T> listener,
                                      Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Comment");
        request.setClassName("GetCommentList");
        request.addParamsNotEmpty("case_id", case_id);
        request.addParamsNotEmpty("token", UserUtil.getToken(App.instance));
        request.addParamsNotEmpty("p", p);
        request.addParamsNotEmpty("psize", "10");

        //  Log.e(TAG, "评论 参数: " + JSONObject.toJSONString(request));
        post(request, listener, clazz);
    }


    // 查看评价
    public static <T> void getCommentInfo(String comment_id,
                                          IHttpResultListener<T> listener,
                                          Class<T> clazz) {

        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("GetCommentInfo");
        request.addParamsNotEmpty("token", UserUtil.getToken(App.instance));
        request.addParamsNotEmpty("comment_id", comment_id);

        post(request, listener, clazz);

    }
}
