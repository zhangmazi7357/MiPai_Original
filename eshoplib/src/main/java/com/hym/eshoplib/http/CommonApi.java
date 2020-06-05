package com.hym.eshoplib.http;

import android.content.Context;

import com.hym.httplib.interfaces.IHttpResultListener;

import java.io.File;

import cn.hym.superlib.utils.http.HttpUtil;
import cn.hym.superlib.utils.user.UserUtil;

import static cn.hym.superlib.utils.http.ApiExcuter.post;

/**
 * Created by 胡彦明 on 2018/2/24.
 * <p>
 * Description 通用接口
 * <p>
 * otherTips
 */

public class CommonApi {

    //上传文件
    public static <T>void uploadFile(Context contexts,File[]files, IHttpResultListener<T>listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Public");
        request.setClassName("AddAtachment");
        request.AddParems("token", UserUtil.getToken(contexts));
        request.setFilesKey("attachment[]");
        request.setFiles(files);
        post(request,listener,clazz);

    }

    //获取搜索历史
    public static <T>void  getSearchHistory(Context context,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Goods");
        request.setClassName("GetKeyWord");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("type","2");//类型-必须（1：租房，2：商城，3：二手物品）
        post(request,listener,clazz);

    }
    //清除搜索历史
    public static <T>void  deleteSearchHistory(Context context,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Goods");
        request.setClassName("DelKeyWord");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("type","2");//类型-必须（1：租房，2：商城，3：二手物品）
        post(request,listener,clazz);

    }
}
