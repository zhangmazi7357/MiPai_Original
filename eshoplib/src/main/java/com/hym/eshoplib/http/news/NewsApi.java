package com.hym.eshoplib.http.news;

import com.hym.httplib.interfaces.IHttpResultListener;

import app.App;
import cn.hym.superlib.utils.http.HttpUtil;
import cn.hym.superlib.utils.user.UserUtil;

import static cn.hym.superlib.utils.http.ApiExcuter.post;

/**
 * Created by 胡彦明 on 2018/3/18.
 * <p>
 * Description 新闻和活动
 * <p>
 * otherTips
 */

public class NewsApi {
    //新闻列表
    public static <T>void getNewsList(String p, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Agency");
        request.setClassName("GetList");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("psize","10");
        request.AddParems("p",p);
        post(request,listener,clazz);

    }
    //阅读次数
    public static <T>void AppendViewinfo(String agency_id, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Agency");
        request.setClassName("AppendViewinfo");
        request.AddParems("token", UserUtil.getToken(App.instance));
        request.AddParems("agency_id",agency_id);
        post(request,listener,clazz);

    }

}
