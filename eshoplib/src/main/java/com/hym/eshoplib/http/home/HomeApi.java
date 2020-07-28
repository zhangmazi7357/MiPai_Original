package com.hym.eshoplib.http.home;

import android.util.Log;

import com.hym.httplib.interfaces.IHttpResultListener;

import app.App;
import cn.hym.superlib.utils.http.HttpUtil;
import cn.hym.superlib.utils.user.UserUtil;

import static cn.hym.superlib.utils.http.ApiExcuter.post;
import static cn.hym.superlib.utils.http.ApiExcuter.postFirstPager;
import static cn.hym.superlib.utils.http.ApiExcuter.postTest;

/**
 * Created by 胡彦明 on 2018/3/18.
 * <p>
 * Description 觅拍首页
 * <p>
 * otherTips
 */

public class HomeApi {

    //详情页推荐列表
    public static <T> void getDetailComment(String type, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Index");
        request.setClassName("GetLike");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize", "10");
        request.AddParems("p", "1");
        postFirstPager(request, type, listener, clazz);
    }

    //产品详情
    public static <T> void getProductDetailData(IHttpResultListener<T> listener, Class<T> clazz, String caseId) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("GetProductionContent");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("case_id", caseId);
        post(request, listener, clazz);
    }

    //首页下边列表之拍视频
    public static <T> void getTakeVedioData(int page, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Index");
        request.setClassName("GetNewvideo");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize", "10");
        request.AddParems("p", page + "");
        postFirstPager(request, "1", listener, clazz);
    }

    //首页下边列表之拍照片
    public static <T> void getTakePhotoData(int page, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Index");
        request.setClassName("GetPhoto");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize", "10");
        request.AddParems("p", page + "");
        postFirstPager(request, "2", listener, clazz);
    }

    //首页下边列表之推荐
    public static <T> void getHomeCommentData(int pager, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Index");
        request.setClassName("GetRecommend");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize", "10");
        request.AddParems("p", pager + "");
        postFirstPager(request, "0", listener, clazz);
    }

    //觅拍严选
    public static <T> void getStrictSelectData(String page, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Index");
        request.setClassName("GetMipai");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize", "10");
        request.AddParems("p", page);
        postFirstPager(request, "0", listener, clazz);
    }

    //获取限时特惠信息
    public static <T> void getSpecialTimeLimteData(String page, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Index");
        request.setClassName("GetDiscount");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize", "10");
        request.AddParems("p", page);
        postFirstPager(request, "0", listener, clazz);
    }

    //获取商城首页数据
    public static <T> void getHomeData(IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Index");
        request.setClassName("GetTop");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request, listener, clazz);
    }

    //产品点赞
    public static <T> void agree(String content_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("AddAgree");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("content_id", content_id);
        post(request, listener, clazz);

    }

    //清除历史
    public static <T> void deleteHistory(IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Goods");
        request.setClassName("DelKeyWord");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request, listener, clazz);

    }

    //获取系统消息
    public static <T> void GetSystemMsg(String p, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetSystemMsg");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("p", p);
        request.AddParems("psize", "10");
        post(request, listener, clazz);

    }

    //定位区域切换
    public static <T> void ChangeRegion(String region_name, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("House");
        request.setClassName("ChangeRegion");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("region_name", region_name);

        post(request, listener, clazz);

    }

    //获取未读消息
    public static <T> void GetNewMsg(IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetNewMsg");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request, listener, clazz);

    }

    //获取订单消息
    public static <T> void GetMsg(String p, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("GetMsg");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize", "10");
        request.AddParems("p", p);
        post(request, listener, clazz);

    }

    //消息已读
    public static <T> void ReadMsg(String msg_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("ReadMsg");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("msg_id", msg_id);
        post(request, listener, clazz);

    }

    //首页接受预约消息列表
    public static <T> void GetAccept(IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("GetAccept");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request, listener, clazz);

    }

    public static <T> void DeleteMsg(String msg_id, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("DeleteMsg");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("msg_id", msg_id);
        post(request, listener, clazz);

    }

    public static <T> void GetArea(String search, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Consignee");
        request.setClassName("GetArea");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("search", search);
        post(request, listener, clazz);

    }

    public static <T> void GetAdvert(IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Index");
        request.setClassName("GetAdvert");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request, listener, clazz);

    }

    public static <T> void GetVideo(String p, IHttpResultListener<T> listener, Class<T> clazz) {
        HttpUtil.BaseHttpRequest request = HttpUtil.getRequest();
        request.setApp("Index");
        request.setClassName("GetVideo");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("p", p);
        request.AddParems("psize", "10");
        post(request, listener, clazz);
    }
}
