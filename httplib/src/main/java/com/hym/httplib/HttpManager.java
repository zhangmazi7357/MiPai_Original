package com.hym.httplib;

import android.content.Context;
import android.util.Log;

import com.hym.httplib.interfaces.IHttpManager;
import com.hym.httplib.interfaces.IHttpResultListener;
import com.hym.httplib.model.HttpRequest;


/**
 * Created by 胡彦明 on 2017/6/29.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class HttpManager {
    private static volatile HttpManager httpManager;
    IHttpManager iHttpManager;

    private HttpManager() {

    }

    public static HttpManager getInstance() {
        if (httpManager == null) {
            synchronized (HttpManager.class) {
                if (httpManager == null) {
                    httpManager = new HttpManager();
                }
            }
        }
        return httpManager;
    }

    //必须调用Init 注入IHttpManager实例
    protected void init(Context context, IHttpManager iHttpManager, boolean debug) {
        this.iHttpManager = iHttpManager;
        this.iHttpManager.init(context, debug);
    }

    public <K, V, R> void post(HttpRequest<K, V> request, IHttpResultListener<R> listener, Class<R> clazz) {
        chekInit();
        iHttpManager.post(request, listener, clazz);
    }


    public <K, V, R> void get(HttpRequest<K, V> request, IHttpResultListener<R> listener, Class<R> clazz) {
        chekInit();
        iHttpManager.get(request, listener, clazz);
    }


    public <K, V, R> void postFile(HttpRequest<K, V> request, IHttpResultListener<R> listener, Class<R> clazz) {
        chekInit();
        iHttpManager.postFile(request, listener, clazz);
    }

    private void chekInit() {
        if (iHttpManager == null) {
            throw new RuntimeException("iHttpManager can not be null");
        }
    }


}
