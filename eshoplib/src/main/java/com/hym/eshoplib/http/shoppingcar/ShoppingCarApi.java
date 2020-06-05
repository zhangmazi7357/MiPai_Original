package com.hym.eshoplib.http.shoppingcar;

import android.content.Context;
import android.text.TextUtils;

import com.hym.httplib.interfaces.IHttpResultListener;

import cn.hym.superlib.utils.http.HttpUtil;
import cn.hym.superlib.utils.user.UserUtil;

import static cn.hym.superlib.utils.http.ApiExcuter.post;

/**
 * Created by 胡彦明 on 2018/3/18.
 * <p>
 * Description 购物车接口
 * <p>
 * otherTips
 */

public class ShoppingCarApi {
    //获取购物车数量
    public static <T>void getShoppingCarCount( Context context,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("GetCount");
        request.AddParems("token", UserUtil.getToken(context));
        post(request,listener,clazz);

    }
    /**
     * 添加购物车
     */
    public static <T>void addToShoppingCar(Context context,String content_id,String quantity,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("CartAdd");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("content_id",content_id);
        request.AddParems("quantity",quantity);
        post(request,listener,clazz);

    }
    /**
     * 获取购物车数据
     * @param listener
     * @param clazz
     * @param <T>
     */
    public static <T>void cartGetAll(Context context,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("CartGetAll");
        request.AddParems("token", UserUtil.getToken(context));
        post(request,listener,clazz);
    }
    /**
     * 修改购物车
     * @param cart_id 购物车ID-必须
     * @param quantity 修改数量-非必须
     * @param status  选中状态-非必需（1:选中，0:未选中）
     * @param listener
     * @param clazz
     * @param <T>
     */
    public static <T>void cartEdit(Context context,String cart_id,String quantity,String status,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("CartEdit");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("cart_id",cart_id);
        if(!TextUtils.isEmpty(quantity)){
            request.AddParems("quantity",quantity);
        }
        if(!TextUtils.isEmpty(status)){
            request.AddParems("status",status);
        }
        post(request,listener,clazz);
    }
    /**
     * 全选或者取消全选购物车
     * @param store_id 店铺id 店铺ID-非必须
     * @param status 选中状态-必需（1:选中，0:不选中）
     * @param listener
     * @param clazz
     * @param <T>
     */
    public static <T>void cartSelectOrCancleAll(Context context,String store_id,String status,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("EditStatus");
        request.AddParems("token", UserUtil.getToken(context));
        if(!TextUtils.isEmpty(store_id)){
            request.AddParems("store_id",store_id);
        }
        if(!TextUtils.isEmpty(status)){
            request.AddParems("status",status);
        }
        post(request,listener,clazz);
    }
    /**
     * 删除购物车
     * @param cart_ids 购物车id
     * @param listener
     * @param clazz
     * @param <T>
     */
    public static <T>void deleGoods(Context context,String cart_ids,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("CartDelete");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("cart_ids",cart_ids);
        post(request,listener,clazz);
    }
    //计算订单价格，创建订单时
    /**
     *
     * @param specification_id 规格
     * @param buy_num 数量
     * @param listener
     * @param clazz
     * @param <T>
     */
    public static <T>void gettTotalForCreateOrder(Context context,String specification_id,String buy_num,String coupon_log_id,
                                                  IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("GetTotal");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("c_type","1");//参数类型  1:立即购买，2:购物车-必须
        request.AddParems("specification_id",specification_id);//商品规格ID-非必需（c_type=1时必须）
        request.AddParems("buy_num",buy_num);//购买数量-非必需（c_type=1时必须）
        if(!TextUtils.isEmpty(coupon_log_id)){
            request.AddParems("coupon_log_id",coupon_log_id);
        }
        post(request,listener,clazz);

    }
    //计算订单价格，购物车中
    /**
     *
     * @param store_id 店铺ID-非必需（计算指定店铺购物车商品时必须）
     * @param listener
     * @param clazz
     * @param <T>
     */
    public static <T>void gettTotalForShoppingCar(Context context,String store_id,String coupon_log_id,IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Bts");
        request.setClassName("GetTotal");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("c_type","2");//参数类型  1:立即购买，2:购物车-必须
        request.AddParems("store_id",store_id);
        if(!TextUtils.isEmpty(coupon_log_id)){
            request.AddParems("coupon_log_id",coupon_log_id);
        }
        post(request,listener,clazz);

    }

}
