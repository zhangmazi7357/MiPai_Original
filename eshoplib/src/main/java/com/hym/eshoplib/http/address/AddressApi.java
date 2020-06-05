package com.hym.eshoplib.http.address;

import android.content.Context;
import android.text.TextUtils;

import com.hym.httplib.interfaces.IHttpResultListener;

import cn.hym.superlib.utils.http.HttpUtil;
import cn.hym.superlib.utils.user.UserUtil;

import static cn.hym.superlib.utils.http.ApiExcuter.post;


/**
 * Created by 胡彦明 on 2017/7/25.
 * <p>
 * Description 地址管理相关
 * <p>
 * otherTips
 */

public class AddressApi {

    /**
     * 获取用户收货地址列表
     * @param listener
     * @param is_default 获取默认地址-非必需（1:获取默认地址）
     * @param clazz
     * @param <T>
     */
    public static <T>void getAddressList(Context context,String is_default, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Consignee");
        request.setClassName("GetAll");
        request.AddParems("psize","100");
        request.AddParems("p","1");
        request.AddParems("token", UserUtil.getToken(context));
        if(!TextUtils.isEmpty(is_default)){
            request.AddParems("is_default",is_default);
        }
        post(request,listener,clazz);

    }

    /**
     * 添加或者编辑收货地址
     */
    public static <T>void addOrEditeAddress(Context context,String isdefault,String id,String name,String phone,String address, boolean isEdit, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Consignee");
        if(isEdit){
            //编辑收货地址
            request.setClassName("Edit");
            request.AddParems("consignee_id",id);//如果是编辑收货地址则需要传入id
        }else{
            //添加收货地址
            request.setClassName("Add");

        }
        request.AddParems("token", UserUtil.getToken(context));
        request.addParamsNotEmpty("name",name);
        request.addParamsNotEmpty("mobile",phone);
        request.addParamsNotEmpty("address",address); //详细地址
        request.addParamsNotEmpty("is_default",isdefault); //是否为默认地址 0否1是
        post(request,listener,clazz);

    }

    /**
     * 删除收获地址
     */
    public static <T>void deleteAddress(Context context,String consignee_id, IHttpResultListener<T> listener, Class<T> clazz){
        HttpUtil.BaseHttpRequest request=HttpUtil.getRequest();
        request.setApp("Consignee");
        request.setClassName("Delete");
        request.AddParems("token", UserUtil.getToken(context));
        request.AddParems("consignee_id",consignee_id);
        post(request,listener,clazz);

    }

}
