package com.hym.eshoplib.http.home;

import android.content.Context;

import com.hym.httplib.interfaces.IHttpResultListener;

import cn.hym.superlib.utils.http.HttpUtil;
import cn.hym.superlib.utils.user.UserUtil;

import static cn.hym.superlib.utils.http.ApiExcuter.post;

/**
 * Created by 胡彦明 on 2018/3/18.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class EshopHomeApi {
    //获取商城首页数据
    public static <T>void getHomeData(Context context,String p, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Goods");
        request.setClassName("GetIndexGoodsList");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("psize","10");
        request.AddParems("p",p);
        post(request,listener,clazz);
    }

    //获取二手物品筛选条件列表
    public static <T>void  getGoodsFilterList(Context context,String store_id,String category_id,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Goods");
        request.setClassName("GetCategoryBase");
        request.AddParems("token", UserUtil.getToken(context));
        request.addParamsNotEmpty("store_id",store_id);
        request.addParamsNotEmpty("category_id",category_id);
        post(request,listener,clazz);

    }
    //搜索商品
    public static <T>void GetGoodsList(Context context,String category_id,String search,String order,String sort,String pageNum, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Goods");
        request.setClassName("GetGoodsList");
        request.AddParems("token", UserUtil.getToken(context));
        request.addParamsNotEmpty("category_id",category_id);
        request.addParamsNotEmpty("search",search);
        request.addParamsNotEmpty("order",order);
        request.addParamsNotEmpty("sort",sort);
        request.AddParems("psize","10");
        request.AddParems("p",pageNum);
        post(request,listener,clazz);

    }
}
