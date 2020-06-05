package com.hym.eshoplib.http.goods;

import android.content.Context;

import com.hym.httplib.interfaces.IHttpResultListener;

import app.App;
import cn.hym.superlib.utils.http.HttpUtil;
import cn.hym.superlib.utils.user.UserUtil;

import static cn.hym.superlib.utils.http.ApiExcuter.post;
import static cn.hym.superlib.utils.http.ApiExcuter.postTest;

/**
 * Created by 胡彦明 on 2018/3/18.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class GoodsApi {

    //获取向我推荐分类
    public static <T>void getCommentForMeData(IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Goods");
        request.setClassName("GetKeyWordtype");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request,listener,clazz);
    }

    //获取商品详情
    public static <T>void getGoodsDetail(Context context,String specification_id, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Goods");
        request.setClassName("GetGoodsDetail");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("specification_id",specification_id);
        post(request,listener,clazz);
    }

    //添加收藏商品
    public static <T>void  changeLike(Context context,String content_id,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("AddFavorite");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("type","goods");
        request.AddParems("content_id",content_id);
        post(request,listener,clazz);

    }
    //获取商品评论
    public static <T>void  GetCommentList(Context context,String specification_id,String rank_type,String psize,String p,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Goods");
        request.setClassName("GetCommentList");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("specification_id",specification_id);
        request.addParamsNotEmpty("rank_type",rank_type);
        request.AddParems("psize",psize);
        request.AddParems("p",p);
        post(request,listener,clazz);

    }
    //添加商品评论
    public static <T>void addGoodsComments(Context context,String comment, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Comment");
        request.setClassName("GoodsAdd");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("comment",comment);
        post(request,listener,clazz);

    }
    //客服咨询
    public static <T>void FeedBack(Context context,String spid,String content, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Cas");
        request.setClassName("FeedBack");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("type","2");
        request.AddParems("content",content);
        request.AddParems("specification_id",spid);
        post(request,listener,clazz);
    }
    /**
     * 获取热词和搜索记录
     * @param listener
     * @param clazz
     * @param <T>
     */
    public static <T>void getSearchHistory(IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Goods");
        request.setClassName("GetKeyWord");
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request,listener,clazz);

    }
    //获取分类
    public static <T>void getCategory(String category_id,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Activity");
        request.setClassName("GetCategory");
        request.addParamsNotEmpty("category_id",category_id);
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request,listener,clazz);

    }
    //获取工作室服务价格
    public static <T>void GetStoreCategory(String store_id,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Store");
        request.setClassName("GetStoreCategory");
        request.addParamsNotEmpty("store_id",store_id);
        request.AddParems("token", UserUtil.getToken(App.instance));
        post(request,listener,clazz);

    }


}
