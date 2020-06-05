package com.hym.httplib.interfaces;

import android.content.Context;

import com.hym.httplib.model.HttpRequest;

/**
 * Created by 胡彦明 on 2017/6/29.
 * <p>
 * Description http 请求抽象接口 k=请求参数Key,V=请求参数value,T自定义实体规则，判断请求结果，是否成功，是否为空等
 * <p>
 * otherTips R=result，请求返回结果 自定义bean
 */

public interface IHttpManager<K, V, T> {
    public void init(Context context, boolean debug);

    public <R> void post(HttpRequest<K, V> request, IHttpResultListener<R> listener, Class<R> clazz);

    public <R> void get(HttpRequest<K, V> request, IHttpResultListener<R> listener, Class<R> clazz);

    public <R> void postFile(HttpRequest<K, V> request, IHttpResultListener<R> listener, Class<R> clazz);

    public boolean isSuccess(T result);

    public boolean isEmpty(T result);
}
